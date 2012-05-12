package edu.bridgeport.mohammad.binary;

public class Byte {
	private char[] bits = { '0', '0', '0', '0', '0', '0', '0', '0' };
	private boolean underflow = false;
	private boolean overflow = false;

	public Byte() {
		// default
	}

	public Byte(String newBits) {
		this(newBits.toCharArray());
	}

	public Byte(char[] newBits) {
		for (int i = 0; i < bits.length; i++) {
			bits[i] = newBits[i]; // will out of range on bits-n != newBits-n
		}
	}

	public Byte(Byte b) {
		char[] other = b.getBits();
		for (int i = 0; i < bits.length; i++) {
			bits[i] = other[i];
		}
	}

	public Byte add(Byte other) {
		Byte res = new Byte();
		char[] resultSet = res.getBits();
		char[] firstSet = bits;
		char[] secondSet = other.getBits();

		int carryOver = 0;
		for (int i = firstSet.length - 1; i >= 0; i--) {
			if (firstSet[i] == '1' && secondSet[i] == firstSet[i]) {
				// both one
				resultSet[i] = '0';
				carryOver++;
			} else if (firstSet[i] != secondSet[i]) {
				// one zero, one one
				// can one of zeros borrow from a carry over?
				if (carryOver > 0) {
					// if so carry again
					resultSet[i] = '0';
				} else {
					resultSet[i] = '1';
				}
			} else {
				// both are zero
				// do we have any carry overs?
				if (carryOver > 0) {
					// if so apply them
					resultSet[i] = '1';
					carryOver--;
				} else {
					// otherwise, set to zero
					resultSet[i] = '0';
				}
			}
		}

		if (carryOver > 0) {
			// Overflow: first result bit is 1 but both a and b's 1st bit is 0
			if (resultSet[0] == '1' && firstSet[0] == '0'
					&& secondSet[0] == '0') {
				res.overflow = true;
				try {
					res.setBits(new char[] {'0','1','1','1','1','1','1','1'});
				} catch (Exception e) {
					e.printStackTrace();
				}
				// Underflow: first result bit is 0 but both a and b's 1st bit
				// is 1
			} else if (resultSet[0] == '0' && firstSet[0] == '1'
					&& secondSet[0] == '1') {
				res.underflow = true;
				try{
					res.setBits(new char[] {'1','0','0','0','0','0','0','0'});
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

		return res;
	}

	private void setBits(char[] cs) throws Exception {
		if(cs.length != 8){
			throw new Exception("Not 8 bits");
		}
		bits = cs;
	}

	public Byte biasedToTwosCompliment() {
		Byte copy = new Byte(this);
		char[] bits = copy.getBits();
		bits[0] = (bits[0] == '0' ? '1' : '0');
		return copy;
	}

	public int magnitude() {
		// This is 2 complements

		int value = 0;
		char[] mangle = new char[bits.length];
		for (int i = 0; i < bits.length; i++)
			mangle[i] = bits[i];

		// if negative
		if (bits[0] == '1') {
			// Flip the bits
			for (int i = 0; i < mangle.length; i++) {
				mangle[i] = (mangle[i] == '0' ? '1' : '0');
			}

			// adds one
			for (int i = mangle.length - 1; i > -1; i--) {
				if (mangle[i] == '0') {
					mangle[i] = '1';
					break;
				} else { // the bit is 1
					mangle[i] = '0';
				}
			}
		}

		// add values
		for (int i = 0; mangle.length > i; i++) {
			if (mangle[i] == '1') {
				value += Math.pow(2, mangle.length - i - 1);
			}
		}

		// return value, prepend negative if negative number
		if (bits[0] == '1') {
			// -(value+1)
			return -value;
		} else {
			return value;
		}
	}

	public char[] getBits() {
		return bits;
	}

	@Override
	public String toString() {
		if (overflow)
			return "Overflow";
		if (underflow)
			return "Underflow";

		StringBuilder builder = new StringBuilder();

		for (char element : bits)
			builder.append(element);
		return builder.toString();
	}
}
