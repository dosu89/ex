package vo;

public class CommentVO {
	private int c_no;
	private int b_no;
	private String c_writer;
	private String c_content;
	
	public CommentVO() {}
	
	public CommentVO(int b_no, String c_writer, String c_content) {
		super();
		this.b_no = b_no;
		this.c_writer = c_writer;
		this.c_content = c_content;
	}
	
	public CommentVO(int c_no, int b_no, String c_writer, String c_content) {
		super();
		this.c_no = c_no;
		this.b_no = b_no;
		this.c_writer = c_writer;
		this.c_content = c_content;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getC_writer() {
		return c_writer;
	}

	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
}
