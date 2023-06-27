package vo;

public class BoardVO {
	private int b_no;
	private String b_title;
	private String b_writer;
	private String b_content;
	
	public BoardVO() {}
	
	public BoardVO(String b_title, String b_writer, String b_content) {
		super();
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_content = b_content;
	}

	public BoardVO(int b_no, String b_title, String b_writer, String b_content) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_content = b_content;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	@Override
	public String toString() {
		return "BoardVO [b_no=" + b_no + ", b_title=" + b_title + ", b_writer=" + b_writer + ", b_content=" + b_content
				+ "]";
	}
	
	
}
