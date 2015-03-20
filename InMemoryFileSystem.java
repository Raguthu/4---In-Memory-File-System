package com.comcast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class InMemoryFileSystem {
	public static void main(String[] args) throws IOException {
		
		InMemoryFileSystem np=new InMemoryFileSystem();


		
		//(1)
		System.out.println("Creating Directory");
		createFolder("C:\\Users\\Raguthu\\Desktop\\Comcast");
		
		//(2)
		System.out.println("Creating File");
		createFile("C:\\Users\\Raguthu\\Desktop\\Comcast");
		
		//(3)
		System.out.println("Appending data");
		appendData("hello world","C:\\Users\\Raguthu\\Desktop\\Comcast\\newfile.txt");
		
		//(4)
		System.out.println("File copying");
		copyFiles("C:\\Users\\Raguthu\\Desktop\\Comcast\\newfile.txt", "C:\\Users\\Raguthu\\Desktop\\Comcast\\newfile2.txt");

		//(5)
		System.out.println("File Content");
		filecontents("C:\\Users\\Raguthu\\Desktop\\Comcast\\newfile.txt");

		//(6)
		System.out.println("ListingFolderContents");
		listFolderContents("C:\\Users\\Raguthu\\Desktop\\Comcast");
		
		//(7) & (8)
		System.out.println("Searching a file by name");
		System.out.println(np.textFiles("/C:\\Users\\Raguthu\\Desktop\\Comcast", "new"));
		

	}
	public static void createFolder(String path){
		File file = new File(path);
		if(!file.exists()){
			if(file.mkdir()){
				System.out.println("directory created");
			}
		}
		else 
			System.out.println("Directory already exists!!");

	}

	public static void createFile(String path){
		File file1 = new File(path+"/newfile.txt");

		try {
			if (file1.createNewFile()){
				System.out.println("File is created!");
			}else{
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}}
	public static void appendData(String data, String path) throws IOException{

		//String data = "hello world!";
		File file3 = new File(path);

		if(!file3.exists()){
			try {
				file3.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		FileWriter fileWritter = new FileWriter(file3,true);

		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		fileWritter.append(data);
		bufferWritter.close();
		System.out.println("Done");
	}
	public static void copyFiles(String path1,String path2)
	{
		InputStream inStream = null;
		OutputStream outStream = null;

		try{

			File afile =new File(path1);
			File bfile =new File(path2);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes 
			while ((length = inStream.read(buffer)) > 0){

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			System.out.println("File is copied successful!");

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void filecontents(String path) throws IOException
	{
		String content = new Scanner(new File(path)).useDelimiter("\\Z").next();
		System.out.println(content);


	}

	public static void listFolderContents(String path)
	{
		File f = new File(path);
		File[] list = f.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].isFile()) {
				System.out.println("File " + list[i].getName());
			} else if (list[i].isDirectory()) {
				System.out.println("Directory " + list[i].getName());
			}
		}
	}
	

	List<String> textFiles(String directory, String fileNmfilter) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().startsWith(fileNmfilter) && file.getName().endsWith((".txt"))) {
		      textFiles.add(file.getName()+file.getAbsolutePath()+"\n");
		    }
		  }
		  return textFiles;
		}
}
