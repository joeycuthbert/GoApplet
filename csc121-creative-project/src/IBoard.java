
public interface IBoard {

}

class NineBoard implements IBoard{
	int points = 9;
	IStone stone;
	
	NineBoard(IStone stone){
	this.stone = stone;
}
}

class ThirteenBoard implements IBoard{
	int points = 13;
	IStone stone;
	
	ThirteenBoard(IStone stone){
	this.stone = stone;
}

}

class NineteenBoard implements IBoard{
	int points = 19;
	IStone stone;
	
	NineteenBoard(IStone stone){
	this.stone = stone;
}

}
