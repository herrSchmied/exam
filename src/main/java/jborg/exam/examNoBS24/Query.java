package jborg.exam.examNoBS24;

import org.springframework.http.ResponseEntity;

public interface Query<O, I>
{
	public ResponseEntity<O> execute(I input);
}
