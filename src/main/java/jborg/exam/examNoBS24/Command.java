package jborg.exam.examNoBS24;

public interface Command <O, I>
{
	public O execute(I input);
}
