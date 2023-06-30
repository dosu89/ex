package vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Reply {
	private int rno;
	private String comment;
	private String writer;
	private Timestamp regdate;
	private int bno;
}
