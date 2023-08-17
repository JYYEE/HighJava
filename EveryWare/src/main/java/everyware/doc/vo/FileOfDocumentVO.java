package everyware.doc.vo;

public class FileOfDocumentVO {
	private String file_path;
	private String doc_id   ;
	private String file_name;
	private long file_size;
	
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	@Override
	public String toString() {
		return "FileOfDocumentVO [file_path=" + file_path + ", doc_id=" + doc_id + ", file_name=" + file_name
				+ ", file_size=" + file_size + "]";
	}
	
}
