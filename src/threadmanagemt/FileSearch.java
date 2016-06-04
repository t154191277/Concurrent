package threadmanagemt;

import java.io.File;

public class FileSearch extends Thread{
	private String fileName;
	private String fileDirectory;
	
	public FileSearch(String fileName, String fileDirectory) {
		super();
		this.fileName = fileName;
		this.fileDirectory = fileDirectory;
	}
	@Override
	public void run() {
			File file = new File(fileDirectory);
			if(file.isDirectory()){
				try {
					DirectoryProcess(file);
				} catch (InterruptedException e) {
					System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
				}
			}
	}
	private void DirectoryProcess(File file) throws InterruptedException{
		File[] fileList = file.listFiles();
		if(fileList != null){
			for(File f : fileList){
				if(f.isDirectory()){
					DirectoryProcess(f);
				}
				else {
					FileProcess(f);
				}
			}
		}
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}
	private void FileProcess(File f) throws InterruptedException{
		if(fileName.equals(f.getName())){
			System.out.printf("%s : %s\n",Thread.currentThread().getName(),f.getAbsolutePath());
		}
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}
}
