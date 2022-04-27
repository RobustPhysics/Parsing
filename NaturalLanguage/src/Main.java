
public class Main
{
	
	public static void main(String[] args)
	{
		/*
		==================================
		EXAMPLES:
		==================================
		Alice encoded a message.
		(See March 25th video at ~28:30 minutes)
		
		
		The driver parked the car.
		(See March 25th video at 29:05)
		(S (MOOD Declarative)
			(SUBJ(NP(DET The) (N driver)))
			(VERB park)
			(TENSE past)
			(NUM 3s)
			(OBJ(NP(DET the) (N car))))
		
		
		We read.
		(30:28)
		(S(MOOD declarative)
			(SUBJ(NP(PRONOUN we)))
			(VERB read)
			(TENSE {present, past}))
		
		==================================
		==================================
		==================================
		*/
		
		/*
		==================================
		STEPS
		==================================
		1) We need a lexicon
			A) Element
				a) Word
					>what is the word to look for?
				b) Type (generally just one type?)
					>verb
					>name
					>wh-pronoun
					>article
					>preposition
					>adjective
					>adverb
				c) Feature (list of features with a list of corresponding values?)
					>NUM
					>TENSE
					>TYPE
					>VERB
					>ROOT
				d) Values
					>Associated with feature
					>May be a list of values
					>NUM
						-3s
						-3p
						-1s
						-1p
						-2s
						-2p
					>TENSE
						-past
						-present
						-infinitive
						-
		2) Build the ATN
			A) Variables (?)
				a) DET = ?
				b) NUM = ?
				c) ADJ = {?}
				d) NOUN = ?
				e) 
			B) Node
				a) Arcs (list)
				b) 
			C) Arc
				a) Arc
				b) Test
				c) Action
		
		==================================
		==================================
		==================================
		*/
		
		/*
		==================================
		MARCH 28 - Transitive and Bitransitive
		==================================
		A transitive verb takes a direct object:
			Ex: She hailed a cab.
				hailed (verb) -> a cab (obj)
			
		A bitransitive is a verb that can take both an indirect and direct object.
			Ex: She hailed me a cab
				hailed (verb) -> me (indirect obj)
				hailed (verb) -> a cab (obj)
				
		==================================
		==================================
		==================================
		*/
		
		String inputSentence = "Alice gave Bob a picture of the boat.";
		
		Lexicon lexicon = Lexicon.getInstance();
		
		ATN_Base atn = new ATN_Base();
		System.out.println("Created object");
		String output = atn.parseSentence("Mary walks");
		System.out.println("Parsed");
		System.out.println("Output = " + output);
	}
	
}
