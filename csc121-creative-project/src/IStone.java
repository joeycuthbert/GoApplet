public interface IStone {

}

class WhiteStone implements IStone{
	IStone white;

	public WhiteStone(IStone white) {
		this.white = white;
	}
	
	
}

class BlackStone implements IStone{
	IStone black;

	public BlackStone(IStone black) {
		this.black = black;
	}
}
