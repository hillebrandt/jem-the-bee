/**
    JEM, the BEE - Job Entry Manager, the Batch Execution Environment
    Copyright (C) 2012-2014   Andrea "Stock" Stocchero
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pepstock.jem.springbatch.tasks.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.pepstock.jem.annotations.AssignChunkContext;
import org.pepstock.jem.annotations.AssignStepContribution;
import org.pepstock.jem.annotations.SetFields;
import org.pepstock.jem.log.LogAppl;
import org.pepstock.jem.springbatch.SpringBatchMessage;
import org.pepstock.jem.springbatch.tasks.JemTasklet;
import org.pepstock.jem.springbatch.tasks.JobsProperties;
import org.pepstock.jem.springbatch.tasks.TaskletException;
import org.pepstock.jem.util.ClassLoaderUtil;
import org.pepstock.jem.util.ReverseURLClassLoader;
import org.pepstock.jem.util.UtilMessage;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author Andrea "Stock" Stocchero
 * @version 2.2
 */
public final class LauncherTasklet extends JemTasklet {

	private static final String MAIN_METHOD = "main";

	private String className = null;
	
	private Object object = null;
	
	private List<String> arguments = null;
	
	private List<String> classPath = null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.jem.springbatch.tasks.JemTasklet#run(org.springframework
	 * .batch.core.StepContribution,
	 * org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus run(StepContribution stepContribution, ChunkContext chunkContext) throws TaskletException {
		if (className == null && object == null){
			LogAppl.getInstance().emit(SpringBatchMessage.JEMS051E, "className");
			throw new TaskletException(SpringBatchMessage.JEMS051E.toMessage().getFormattedMessage("className"));
		}
		
		// load by Class.forName
		Class<?> clazz;
		// checks if I have to use the class name or the object
		if (className != null) {
			// uses the class name
			try {
				if (classPath == null || classPath.isEmpty()){
					clazz = Class.forName(className);
				} else {
					clazz = loadMainClass(className);
				}
			} catch (ClassNotFoundException e) {
				LogAppl.getInstance().emit(SpringBatchMessage.JEMS052E, e, className);
				throw new TaskletException(SpringBatchMessage.JEMS052E.toMessage().getFormattedMessage(className), e);
			} catch (IOException e) {
				LogAppl.getInstance().emit(SpringBatchMessage.JEMS052E, e, className);
				throw new TaskletException(SpringBatchMessage.JEMS052E.toMessage().getFormattedMessage(className), e);
			}
		} else {
			// here uses the object class
			clazz = object.getClass();
		}

		// checks if has got a public static void main
		if (hasMainMethod(clazz)) {
			try {
			
				// sets annottions here ONLY if no classpath is set
				if (classPath == null || classPath.isEmpty()){
					// replaces filed annotations
					SetFields.applyByAnnotation(clazz);
					applyByAnnotation(clazz, stepContribution, chunkContext);
				} else{
					clazz = clazz.getClassLoader().loadClass(JavaMainClassLauncher.class.getName());
					arguments.add(className);
				}
				// init params accordingly
				String[] params = null; 
				// loads params
				if ((arguments != null) && (!arguments.isEmpty())){
					params = arguments.toArray(new String[0]);
				}
				
				// invokes main method
				Method main = clazz.getMethod(MAIN_METHOD, String[].class);
				main.invoke(null, (Object) params);
				return RepeatStatus.FINISHED;
			} catch (SecurityException e) {
				throw new TaskletException(e);
			} catch (IllegalArgumentException e) {
				throw new TaskletException(e);
			} catch (NoSuchMethodException e) {
				throw new TaskletException(e);
			} catch (IllegalAccessException e) {
				throw new TaskletException(e);
			} catch (InvocationTargetException e) {
				throw new TaskletException(e);
			} catch (NamingException e) {
				throw new TaskletException(e);
			} catch (ClassNotFoundException e) {
				throw new TaskletException(e);
			}
		} else {
			// HERE is a TASKLET and NOT a MAIN program
			// gets the instance
			Object instance;
			try {
				// if none set the object, load new object
				if (object == null){
					instance = clazz.newInstance();
				} else {
					// uses the reference
					instance = object;
				}
				// applies all annotations
				SetFields.applyByAnnotation(instance);
			} catch (InstantiationException e) {
				throw new TaskletException(e);
			} catch (IllegalAccessException e) {
				throw new TaskletException(e);
			} catch (NamingException e) {
				throw new TaskletException(e);
			}
			// check if it's a JemWorkItem. if not,
			// exception occurs.
			if ((instance instanceof Tasklet) && !(instance instanceof JemTasklet)) {
				Tasklet customtasklet = (Tasklet) instance;
				try {
					return customtasklet.execute(stepContribution, chunkContext);
				} catch (Exception e) {
					throw new TaskletException(e);
				}
			} else {
				LogAppl.getInstance().emit(SpringBatchMessage.JEMS050E, className);
				throw new TaskletException(SpringBatchMessage.JEMS050E.toMessage().getFormattedMessage(className));
			}
		}
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}


	/**
	 * @return the arguments
	 */
	public List<String> getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the classPath
	 */
	public List<String> getClassPath() {
		return classPath;
	}

	/**
	 * @param classPath the classPath to set
	 */
	public void setClassPath(List<String> classPath) {
		this.classPath = classPath;
	}

	/**
	 * Is a static method which checks if the passed class has got a
	 * <code>main</code> method.
	 * 
	 * @param clazz class to be checked
	 * @return <code>true</code> if the class has got the main method
	 */
	private boolean hasMainMethod(Class<?> clazz) {
		try {
			Method method = clazz.getMethod(MAIN_METHOD, String[].class);
			return Modifier.isStatic(method.getModifiers());
		} catch (Exception e) {
			LogAppl.getInstance().ignore(e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * Assigns the value of step contribution and chunk context 
	 * @param clazz class for reflection
	 * @param stepContribution step contribution, passed by SpringBatch core
	 * @param chunkContext chunk context, passed by SpringBatch core
	 * @throws IllegalAccessException if any error occurs
	 */
	private void applyByAnnotation(Class<?> clazz, StepContribution stepContribution, ChunkContext chunkContext) throws IllegalAccessException {
		// scans all declared fields
		for (Field field : clazz.getDeclaredFields()){
			// if has got data description annotation
			if (field.isAnnotationPresent(AssignStepContribution.class) && Modifier.isStatic(field.getModifiers())){
				FieldUtils.writeStaticField(field, stepContribution, true);
			} else if (field.isAnnotationPresent(AssignChunkContext.class) && Modifier.isStatic(field.getModifiers())){
				FieldUtils.writeStaticField(field, chunkContext, true);
			}
		}
	}

	/**
	 * Loads main java class from className
	 * @param className classname to be loaded
	 * @return class object loaded from classpath
	 * @throws IOException if any error occurs
	 * @throws ClassNotFoundException if any error occurs
	 */
	private Class<?> loadMainClass(String classNam) throws IOException, ClassNotFoundException{
		// CLASSPATH has been set therefore it an try to load the plugin by
		// a custom classloader
		// collection of all file of classpath
		Collection<File> files = new LinkedList<File>();

		for (String classPathFile : classPath) {
			classPathFile = FilenameUtils.normalize(classPathFile, true);
			// checks if a item contains more than 1 path
			String[] paths = null;
			if (classPathFile.contains(File.separator)){
				// substitute variables if there are and split
				paths = StringUtils.split(JobsProperties.getInstance().replacePlaceHolders(classPathFile), File.separator);
			} else if (classPathFile.contains(";")){
				// substitute variables if there are and split
				paths = StringUtils.split(JobsProperties.getInstance().replacePlaceHolders(classPathFile), ";");
			} else {
				// substitute variables if there are and assign
				paths = new String[]{JobsProperties.getInstance().replacePlaceHolders(classPathFile)};
			}
			if (paths != null){
				for (String path : paths){
					// creates the file
					File file = new File(path);
					// if file ends with * could be only this folder or all folders
					// in cascade
					if (path.endsWith(ClassLoaderUtil.ALL_FOLDER)) {
						// checks if is all folders in cascade
						boolean cascade = path.endsWith(ClassLoaderUtil.ALL_FOLDER_IN_CASCADE);
						// gets the parent and asks for all JAR files
						File parent = file.getParentFile();
						Collection<File> newFiles = FileUtils.listFiles(parent, ClassLoaderUtil.EXTENSIONS, cascade);
						// loads to the collection
						files.addAll(newFiles);
					} else if (file.isDirectory() && file.exists()) {
						// if here, we have a directory
						// adds the directory to collection
						files.add(file);
					} else if (file.isFile() && file.exists()) {
						// if here, a file has been indicated
						// adds the directory to collection
						files.add(file);
					}
				}
			}
		}
		// checks if the collection is empty.
		// if yes, all classpath definiton is wrong and no files have been
		// loaded
		if (!files.isEmpty()) {
			// gets where the class is located
			// because it must be added to classpath
			CodeSource codeSource = JavaMainClassLauncher.class.getProtectionDomain().getCodeSource();
			if ( codeSource != null) {
				// gets URL
				URL url = codeSource.getLocation();
				if (url != null){
					// adds URL to classpath
					files.add(FileUtils.toFile(url));
				}
			}
			// exports files in URLs, for our classloader
			final URL[] urls = FileUtils.toURLs(files.toArray(new File[files.size()]));
			// loads a our classloader by access controller
			ClassLoader loader = AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
				public ClassLoader run() {
					return new ReverseURLClassLoader(urls, LauncherTasklet.class.getClassLoader(), false);
				}
			});
			// loads the plugin from classloader
			return loader.loadClass(className);
		} else {
			throw new IOException(UtilMessage.JEMB009E.toMessage().getMessage());
		}
	}
}
