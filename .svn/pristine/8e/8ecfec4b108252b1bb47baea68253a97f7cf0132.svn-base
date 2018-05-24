package com.cbt.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.LoggerFactory;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class ZipUtil {

	private static final org.slf4j.Logger log = LoggerFactory
			.getLogger(ZipUtil.class);
	private static final int BUFFER = 2048;

	/**
	 * 解压zip文件:支持中文文件
	 * 
	 * @param filePath
	 * @param upZipPath
	 * @throws Exception 
	 */
	public static int unZip(String filePath, String upZipPath) throws Exception {
		List<File> list = new ArrayList<File>();
		int result = 0;
		int count = -1;
		File file = null;
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;

		// 生成指定的保存目录
		String savePath = upZipPath;
		if (!new File(savePath).exists()) {
			new File(savePath).mkdirs();
		}
		ZipFile zipFile = new ZipFile(filePath, "GBK");
		Enumeration enu = zipFile.getEntries();
		while (enu.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) enu.nextElement();
			if (zipEntry.isDirectory()) {
				new File(savePath + "/" + zipEntry.getName().replaceAll(" ", "")).mkdirs();
				continue;
			}
			if (zipEntry.getName().indexOf("/") != -1) {
				new File(savePath+ "/"+zipEntry.getName().substring(0,zipEntry.getName().lastIndexOf("/")).replaceAll(" ", "")).mkdirs();
			}
			is = zipFile.getInputStream(zipEntry);
			file = new File(savePath + "/" + zipEntry.getName().replaceAll(" ", "").replace(".ali", ".jpg"));
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos, BUFFER);

			byte buf[] = new byte[BUFFER];
			while ((count = is.read(buf)) > -1) {
				bos.write(buf, 0, count);
			}
			bos.flush();
			fos.close();
			is.close();
			list.add(file);
		}
		zipFile.close();
		result = 1;
		return result;
	}

	/**
	 * 解压rar文件:支持中文文件
	 * @param filePath
	 * @param upRarPath
	 * @return
	 * @throws Exception 
	 */
	public static int unRar(String filePath, String upRarPath) throws Exception {
		int result = 0;
		Archive a = null;
		FileOutputStream fos = null;
		a = new Archive(new File(filePath));
		FileHeader fh = a.nextFileHeader();
		while (fh != null) {
			if (!fh.isDirectory()) {
				String compressFileName = "";
				if (fh.isUnicode()) {// Unicode文件名使用getFileNameW
					compressFileName = fh.getFileNameW().trim();
				} else {
					compressFileName = fh.getFileNameString().trim();
				}
				// 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
//					String compressFileName = fh.getFileNameString().trim();
				String destFileName = "";
				String destDirName = "";
				// 非windows系统
				if (File.separator.equals("/")) {
					destFileName = upRarPath
							+ compressFileName.replaceAll("\\\\", "/").replaceAll(" ", "").replace(".ali", ".jpg");;
					destDirName = destFileName.substring(0,
							destFileName.lastIndexOf("/")).replaceAll(" ", "");
					// windows系统
				} else {
					destFileName = upRarPath
							+ compressFileName.replaceAll("/", "\\\\").replaceAll(" ", "").replace(".ali", ".jpg");;
					destDirName = destFileName.substring(0,
							destFileName.lastIndexOf("\\")).replaceAll(" ", "");
				}
				// 2创建文件夹
				File dir = new File(destDirName);
				if (!dir.exists() || !dir.isDirectory()) {
					dir.mkdirs();
				}
				// 3解压缩文件
				fos = new FileOutputStream(new File(destFileName));
				a.extractFile(fh, fos);
				fos.close();
				fos = null;
			}
			fh = a.nextFileHeader();
		}
		a.close();
		a = null;
		result = 1;
		return result;
	}

	/**
	 * 创建压缩文件
	 * @param sourcePath
	 * @param zipPath
	 * @throws Exception
	 */
	public static void createZip(String sourcePath, String zipPath) throws Exception {  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
        fos = new FileOutputStream(zipPath);  
        zos = new ZipOutputStream(fos);  
        writeZip(new File(sourcePath), "", zos);  
        zos.close();  
    }  
    /**
     * 写入压缩文件
     * @param file
     * @param parentPath
     * @param zos
     * @throws Exception
     */
    private static void writeZip(File file, String parentPath, ZipOutputStream zos) throws Exception {  
        if(file.exists()){  
            //处理文件夹  
            if(file.isDirectory()){  
                parentPath+=file.getName()+File.separator;  
                File [] files=file.listFiles();  
                for(File f:files){  
                    writeZip(f, parentPath, zos);  
                }  
            }else{  
                FileInputStream fis=null;  
                DataInputStream dis=null;  
                fis=new FileInputStream(file);  
                dis=new DataInputStream(new BufferedInputStream(fis));  
                ZipEntry ze = new ZipEntry(parentPath + file.getName());  
                zos.putNextEntry(ze);  
                //添加编码，如果不添加，当文件以中文命名的情况下，会出现乱码  
                // ZipOutputStream的包一定是apache的ant.jar包。JDK也提供了打压缩包，但是不能设置编码  
                zos.setEncoding("GBK");  
                byte [] content=new byte[1024];  
                int len;  
                while((len=fis.read(content))!=-1){  
                    zos.write(content,0,len);  
                    zos.flush();  
                }  
                dis.close();  
            }  
        }  
    }

       

    
  //文件打包下载
    public static HttpServletResponse downLoadFiles(List<File> files,File file,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件*/
            //List<File> files = new ArrayList<File>();
     
            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip*/
//             file = new File("e:\\certpics.rar");
            if (!file.exists()){   
                file.createNewFile();   
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);   
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
           ZipOutputStream zipOut 
            = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream*/
           zipFile(files, zipOut);
            zipOut.close();
            fous.close();
           return downloadZip(file,response);
        }catch (Exception e) {
                e.printStackTrace();
            }
            /**直到文件的打包已经成功了，
             * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
             * 稍后会呈现出来，接下来的就是往客户端写数据了*/
           
     
        return response ;
    }

  /**
     * 把接受的全部文件打成压缩包 
     * @param List<File>;  
     * @param org.apache.tools.zip.ZipOutputStream  
     */
    public static void zipFile
            (List files,ZipOutputStream outputStream) {
        int size = files.size();
        for(int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    public static HttpServletResponse downloadZip(File file,HttpServletResponse response) {
        try {
        // 以流的形式下载文件。
        InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        // 清空response
        response.reset();

        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");

//如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
        response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(file.getName(), "UTF-8"));
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        } catch (IOException ex) {
        ex.printStackTrace();
        }finally{
             try {
                    File f = new File(file.getPath());
                    f.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return response;
    }

/**  
     * 根据输入的文件与输出流对文件进行打包
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(File inputFile,
            ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据   
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象   
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
}
    
    
	

