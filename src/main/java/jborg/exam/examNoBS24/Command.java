package jborg.exam.examNoBS24;

import org.springframework.http.ResponseEntity;

public interface Command <O, I>
{
	public ResponseEntity<O> execute(I input);
}
