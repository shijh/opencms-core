/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/main/OpenCms.java,v $
 * Date   : $Date: 2003/08/14 17:43:33 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * Copyright (C) 2002 - 2003 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.main;

import org.opencms.db.CmsDefaultUsers;
import org.opencms.loader.CmsLoaderManager;
import org.opencms.site.CmsSiteManager;
import org.opencms.staticexport.CmsLinkManager;
import org.opencms.staticexport.CmsStaticExportProperties;

import com.opencms.core.CmsException;
import com.opencms.file.CmsObject;
import com.opencms.file.I_CmsRegistry;
import com.opencms.flex.CmsEvent;
import com.opencms.flex.I_CmsEventListener;

import java.util.List;

/**
 * The OpenCms "operating system" that provides 
 * public static methods which can be used by other classes to access 
 * basic system features of OpenCms like logging etc.<p>
 * 
 * @author Alexander Kandzior (a.kandzior@alkacon.com)
 * 
 * @version $Revision: 1.2 $
 */
public final class OpenCms {
    
    /**
     * The public contructor is hidden to prevent generation of instances of this class.<p> 
     */
    private OpenCms() {
    }

    /**
     * Add a cms event listener.<p>
     *
     * @param listener the listener to add
     */
    public static void addCmsEventListener(I_CmsEventListener listener) {
        OpenCmsCore.getInstance().addCmsEventListener(listener);
    }

    /**
     * Notify all container event listeners that a particular event has
     * occurred for this Container.<p>  
     * 
     * The default implementation performs
     * this notification synchronously using the calling thread.<p>
     *
     * @param event a CmsEvent
     */
    public static void fireCmsEvent(CmsEvent event) {
        OpenCmsCore.getInstance().fireCmsEvent(event);
    }

    /**
     * Notify all container event listeners that a particular event has
     * occurred for this Container.<p>  
     * 
     * The default implementation performs
     * this notification synchronously using the calling thread.<p>
     *
     * @param cms an initialized CmsObject
     * @param type event type
     * @param data event data
     */
    public static void fireCmsEvent(CmsObject cms, int type, java.util.Map data) {
        OpenCms.fireCmsEvent(new CmsEvent(cms, type, data));
    }

    /**
     * Return the OpenCms default character encoding.<p>
     * 
     * The default is set in the "opencms.properties" file.
     * If this is not set in "opencms.properties" the default 
     * is "ISO-8859-1".<p>
     * 
     * @return the default encoding, e.g. "UTF-8" or "ISO-8859-1"
     */
    public static String getDefaultEncoding() {
        return OpenCmsCore.getInstance().getDefaultEncoding();
    }
    
    /**
     * Returns the configured list of default directory file names.<p>
     *  
     * Caution: This list can not be modified.<p>
     * 
     * @return the configured list of default directory file names
     */
    public static List getDefaultFilenames() {
        return OpenCmsCore.getInstance().getDefaultFilenames();
    }
    
    /**
     * Returns the default user and group name configuration.<p>
     * 
     * @return the default user and group name configuration
     */
    public static CmsDefaultUsers getDefaultUsers() {
        return OpenCmsCore.getInstance().getDefaultUsers();
    }
    
    /**
     * Returns the link manager to resolve links in &lt;link&gt; tags.<p>
     * 
     * @return  the link manager to resolve links in &lt;link&gt; tags
     */
    public static CmsLinkManager getLinkManager() {
        return OpenCmsCore.getInstance().getLinkManager();        
    }

    /**
     * Returns the loader manager used for loading individual resources.<p>
     * 
     * @return the loader manager used for loading individual resources
     */
    public static CmsLoaderManager getLoaderManager() {
        return OpenCmsCore.getInstance().getLoaderManager();
    }

    /**
     * Returns the filename of the logfile.<p>
     * 
     * @return The filename of the logfile.
     */
    public static String getLogFileName() {
        return OpenCmsCore.getInstance().getLogFileName();
    }
    
    /**
     * Returns the mime type for a specified file.<p>
     * 
     * @param filename the file name to check the mime type for
     * @param encoding default encoding in case of mime types is of type "text"
     * @return the mime type for a specified file
     */
    public static String getMimeType(String filename, String encoding) {        
        return OpenCmsCore.getInstance().getMimeType(filename, encoding);              
    }

    /**
     * Returns the OpenCms request context, e.g. /opencms/opencms.<p>
     * 
     * The context will always start with a "/" and never have a trailing "/".<p>
     * 
     * @return String the OpenCms request context, e.g. /opencms/opencms
     */
    public static String getOpenCmsContext() {
        return OpenCmsCore.getInstance().getOpenCmsContext();
    }

    /**
     * Returns the Class that is used for the password validation.<p>
     * 
     * @return the Class that is used for the password validation
     */
    public static String getPasswordValidatingClass() {
        return OpenCmsCore.getInstance().getPasswordValidatingClass();
    }

    /**
     * Returns the registry to read values from it.<p>
     * 
     * You don't have the permissions to write values. 
     * This is useful for modules to read module-parameters.<p>
     *
     * @return the registry
     * @throws CmsException if the registry can not be returned
     */
    public static I_CmsRegistry getRegistry() throws CmsException {
        return OpenCmsCore.getInstance().getRegistry();
    }

    /** 
     * Looks up a value in the runtime property Map.<p>
     *
     * @param key the key to look up in the runtime properties
     * @return the value for the key, or null if the key was not found
     */
    public static Object getRuntimeProperty(Object key) {
        return OpenCmsCore.getInstance().getRuntimeProperty(key);
    }

    /**
     * Returns the initialized site manager, 
     * which contains information about all configured sites.<p> 
     * 
     * @return the initialized site manager
     */
    public static CmsSiteManager getSiteManager() {
        return OpenCmsCore.getInstance().getSiteManager();
    }

    /**
     * Returns the properties for the static export.<p>
     * 
     * @return the properties for the static export
     */
    public static CmsStaticExportProperties getStaticExportProperties() {
        return OpenCmsCore.getInstance().getStaticExportProperties();
    }

    /**
     * Returns the value for the default user access flags.<p>
     * 
     * @return the value for the default user access flags
     */
    public static int getUserDefaultAccessFlags() {
        return OpenCmsCore.getInstance().getUserDefaultAccessFlags();
    }

    /**
     * Returns the value of the user default language.<p>
     * 
     * @return the value of the user default language
     */
    public static String getUserDefaultLanguage() {
        return OpenCmsCore.getInstance().getUserDefaultLanguage();
    }

    /**
     * Returns a String containing the version information (version name and version number) 
     * of this OpenCms system.<p>
     *
     * @return version a String containing the version information
     */
    public static String getVersionName() {
        return OpenCmsCore.getInstance().getVersionName();
    }

    /**
     * Returns a String containing the version number 
     * of this OpenCms system.<p>
     *
     * @return version a String containing the version number
     */
    public static String getVersionNumber() {
        return OpenCmsCore.getInstance().getVersionNumber();
    }
    
    /**
     * Returns an initialized CmsObject with "Guest" user permissions.<p>
     * 
     * @return an initialized CmsObject with "Guest" user permissions
     * @throws CmsException in case of errors initializing the CmsObject
     */    
    public static CmsObject initGuestUser() throws CmsException {
        return OpenCmsCore.getInstance().initGuestUser();
    }

//    /**
//     * Checks if the system logging is active.<p>
//     * 
//     * @return <code>true</code> if the logging is active, <code>false</code> otherwise.
//     */
//    public static boolean isLogging() {
//        return OpenCmsCore.getInstance().isLogging();
//    }

    /**
     * Checks if the system logging is active for the selected channel.<p>
     * 
     * @param channel the channel where to log the message to
     * @return <code>true</code> if the logging is active for the channel, <code>false</code> otherwise.
     */
    public static boolean isLogging(String channel) {
        return OpenCmsCore.getInstance().isLogging(channel);
    }

    /**
     * Logs a message into the OpenCms logfile.<p>
     * 
     * If the logfile was not initialized (e.g. due tue a missing
     * ServletConfig while working with the console)
     * any log output will be written to <code>System.err</code>.
     * 
     * @param channel The channel the message is logged into
     * @param message The message to be logged.
     */
    public static void log(String channel, String message) {
        OpenCmsCore.getInstance().log(channel, message);
    }

    /**
     * Removes a cms event listener.<p>
     *
     * @param listener the listener to remove
     */
    public static void removeCmsEventListener(I_CmsEventListener listener) {
        OpenCmsCore.getInstance().removeCmsEventListener(listener);
    }

    /**       
     * This method adds an Object to the OpenCms runtime properties.
     * The runtime properties can be used to store Objects that are shared
     * in the whole system.<p>
     *
     * @param key the key to add the Object with
     * @param value the value of the Object to add
     */
    public static void setRuntimeProperty(Object key, Object value) {
        OpenCmsCore.getInstance().setRuntimeProperty(key, value);
    }
}
