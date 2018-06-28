package org.usfirst.frc.team5254.robot.autocommands.pathing;

public class Paths {	
	public static class FROM_CENTER {
		/**Right**/
        public static final Path SWITCH_RIGHT_FORWARD = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":50},"mid1":{"x":46,"y":48},"mid2":{"x":51,"y":109},"end":{"x":104,"y":96}} */
        		(-6 + 378 * t + -411 * Math.pow(t, 2))/ (138 + -246 * t + 267 * Math.pow(t, 2)) 
        		, 120));
        
        public static final Path SWITCH_RIGHT_BACKWARD = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":12,"y":40},"mid1":{"x":47,"y":40},"mid2":{"x":47,"y":96},"end":{"x":104,"y":96}} */
        		(0 + 336 * t + -336 * Math.pow(t, 2))/ (105 + -210 * t + 276 * Math.pow(t, 2)) 
        		, 113)
        		);
        
        public static final Path RIGHT_SWITCH_AFTER_GRAB_CUBE = new Path(
        		new PathSegment(t -> 
                /* {"start":{"x":0,"y":100},"mid1":{"x":33,"y":101},"mid2":{"x":0,"y":145},"end":{"x":76,"y":150}} */
                (3 + 258 * t + -246 * Math.pow(t, 2))/ (99 + -396 * t + 525 * Math.pow(t, 2)) 
                , 99)
        		);
        
        public static final Path RIGHT_SIDE_AFTER_GRAB_CUBE = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":55,"y":170},"mid1":{"x":125,"y":170},"mid2":{"x":65,"y":290},"end":{"x":165,"y":300}} */
        		(0 + 720 * t + -690 * Math.pow(t, 2))/ (210 + -780 * t + 870 * Math.pow(t, 2)) 
        		, 189)
        		);
        
        /**Left**/
        public static final Path SWITCH_LEFT_FORWARD = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":30},"end":{"x":108,"y":32}} */
        		(-3 + -408 * t + 417 * Math.pow(t, 2))/ (138 + -246 * t + 279 * Math.pow(t, 2)) 
        		, 133));
        public static final Path SWITCH_LEFT_BACKWARD = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":99},"mid2":{"x":51,"y":30},"end":{"x":104,"y":32}} */
        		(-3 + -408 * t + 417 * Math.pow(t, 2))/ (138 + -246 * t + 267 * Math.pow(t, 2)) 
        		, 130));
        
        public static final Path LEFT_SWITCH_AFTER_GRAB_CUBE = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":33,"y":99},"mid2":{"x":0,"y":55},"end":{"x":80,"y":45}} */
        		(-3 + -258 * t + 231 * Math.pow(t, 2))/ (99 + -396 * t + 537 * Math.pow(t, 2)) 
        		, 105)
        		);
        
        public static final Path LEFT_SIDE_AFTER_GRAB_CUBE = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":150},"mid1":{"x":70,"y":150},"mid2":{"x":10,"y":40},"end":{"x":110,"y":30}} */
        		(0 + -660 * t + 630 * Math.pow(t, 2))/ (210 + -780 * t + 870 * Math.pow(t, 2)) 
        		, 181)
        		);
        
        /**Second Cube**/
        public static final Path GRAB_SECOND_CUBE_FORWARD = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":13,"y":98},"mid2":{"x":44,"y":97},"end":{"x":64,"y":97}} */
        		(-6 + 6 * t + 0 * Math.pow(t, 2))/ (39 + 108 * t + -87 * Math.pow(t, 2)) 
        		, 65)
        );
        
        public static final Path GRAB_SECOND_CUBE_BACKWARD = new Path(
//        		new PathSegment(t -> 
//        		/* {"start":{"x":100,"y":100},"mid1":{"x":122,"y":105},"mid2":{"x":130,"y":105},"end":{"x":135,"y":94}} */
//        		(15 + -30 * t + -18 * Math.pow(t, 2))/ (66 + -84 * t + 33 * Math.pow(t, 2)) 
//        		, 40)
        		
        		new PathSegment(t -> 
        		/* {"start":{"x":100,"y":100},"mid1":{"x":122,"y":105},"mid2":{"x":130,"y":105},"end":{"x":137,"y":94}} */
        		(15 + -30 * t + -18 * Math.pow(t, 2))/ (66 + -84 * t + 39 * Math.pow(t, 2)) 
        		, 41)
        		);
    }
/**	
 *  ===============================================================================================================================
 *															Right
 *	===============================================================================================================================
 **/
    public static class FROM_RIGHT {
    	/**Right Switch**/
        public static final Path SWITCH_RIGHT_TRAVEL = straightLength(100);
        public static final Path SWITCH_RIGHT_FINISH = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":12,"y":103},"mid2":{"x":31,"y":92},"end":{"x":30,"y":80}} */
        		(9 + -84 * t + 39 * Math.pow(t, 2))/ (36 + 42 * t + -81 * Math.pow(t, 2)) 
        		, 41)
                );
        
        public static final Path BACK_SWITCH_PLACE = new Path(
//        		new PathSegment(t -> 
//        		/* {"start":{"x":95,"y":46},"mid1":{"x":159,"y":-38},"mid2":{"x":180,"y":28},"end":{"x":227,"y":28}} */
//        		(-252 + 900 * t + -648 * Math.pow(t, 2))/ (192 + -258 * t + 207 * Math.pow(t, 2)) 
//        		, 154)
        		new PathSegment(t -> 
        		/* {"start":{"x":95,"y":46},"mid1":{"x":159,"y":-38},"mid2":{"x":180,"y":10},"end":{"x":210,"y":10}} */
        		(-252 + 792 * t + -540 * Math.pow(t, 2))/ (192 + -258 * t + 156 * Math.pow(t, 2)) 
        		, 137)
        		);
        
        public static final Path BACK_SWITCH_SECOND_CUBE = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":0},"mid1":{"x":45,"y":21},"mid2":{"x":40,"y":-10},"end":{"x":40,"y":-28}} */
        		(63 + -312 * t + 195 * Math.pow(t, 2))/ (135 + -300 * t + 165 * Math.pow(t, 2)) 
        		, 70)
        		);
        
        public static final Path BACK_SWITCH_BACKUP = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":218,"y":183},"mid1":{"x":198,"y":172},"mid2":{"x":172,"y":134},"end":{"x":147,"y":107}} */
        		(-33 + -162 * t + 114 * Math.pow(t, 2))/ (-60 + -36 * t + 21 * Math.pow(t, 2)) 
        		, 105)
        		);
      
        /**Right Scale**/
        public static final Path SCALE_RIGHT_TRAVEL = straightLength(224);
        public static final Path SCALE_RIGHT_FINISH = new Path(
                new PathSegment(t ->
                        /* {"start":{"x":0,"y":100},"mid1":{"x":10,"y":98},"mid2":{"x":14,"y":98},"end":{"x":28,"y":85}} */
                        (-6 + 12 * t + -45 * Math.pow(t, 2)) / (30 + -36 * t + 48 * Math.pow(t, 2))
                        , 31));//33
        
        /**Left Scale**/
        public static final Path SCALE_LEFT_TRAVEL = new Path(
                straightLength(156).getPathAtDistance(10));
        public static final Path SCALE_LEFT_TRAVEL_2 = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":200},"mid1":{"x":60,"y":205},"mid2":{"x":35,"y":117},"end":{"x":39,"y":45}} */
        		(15 + -558 * t + 327 * Math.pow(t, 2))/ (180 + -510 * t + 342 * Math.pow(t, 2)) 
        		, 180));
        public static final Path SCALE_LEFT_FINISH = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":60,"y":100},"mid2":{"x":45,"y":117},"end":{"x":36,"y":158}} */
        		(0 + 102 * t + 21 * Math.pow(t, 2))/ (180 + -450 * t + 243 * Math.pow(t, 2)) 
        		, 95));
        
//        public static final Path SECOND_CUBE = new Path(
//                new PathSegment(t ->
//                        /* {"start":{"x":100,"y":100},"mid1":{"x":126,"y":110},"mid2":{"x":106,"y":63},"end":{"x":68,"y":35}} */
//                        (30 + -342 * t + 228 * Math.pow(t, 2)) / (78 + -276 * t + 84 * Math.pow(t, 2))
//                        , 92)
//        );
        
        
        public static final Path SCALE_LEFT_TRAVEL_2_CUT_SHORT = new Path(
//        		new PathSegment(t -> 
//        		/* {"start":{"x":0,"y":0},"mid1":{"x":84,"y":5},"mid2":{"x":70,"y":-77},"end":{"x":70,"y":-92}} */
//        		(15 + -522 * t + 462 * Math.pow(t, 2))/ (252 + -588 * t + 336 * Math.pow(t, 2)) 
//        		, 138)
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":200},"mid1":{"x":84,"y":205},"mid2":{"x":70,"y":123},"end":{"x":70,"y":105}} */
        		(15 + -522 * t + 453 * Math.pow(t, 2))/ (252 + -588 * t + 336 * Math.pow(t, 2)) 
        		, 141)
        		
        		);
    }

    
/**	
 *	===============================================================================================================================
 *																Left
 *	=============================================================================================================================== 
 **/   
    public static class FROM_LEFT {
    	/** Switch Left **/
        public static final Path SWITCH_LEFT_TRAVEL = straightLength(100);
        public static final Path SWITCH_LEFT_FINISH = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":12,"y":97},"mid2":{"x":31,"y":108},"end":{"x":30,"y":120}} */
        		(-9 + 84 * t + -39 * Math.pow(t, 2))/ (36 + 42 * t + -81 * Math.pow(t, 2)) 
        		, 41)
                );
    	
        
        public static final Path BACK_SWITCH_PLACE = new Path(
//        		new PathSegment(t -> 
//        		/* {"start":{"x":90,"y":46},"mid1":{"x":164,"y":100},"mid2":{"x":180,"y":64},"end":{"x":200,"y":64}} */
//        		(162 + -540 * t + 378 * Math.pow(t, 2))/ (222 + -348 * t + 186 * Math.pow(t, 2)) 
//        		, 120)
        		new PathSegment(t -> 
        		/* {"start":{"x":90,"y":46},"mid1":{"x":164,"y":100},"mid2":{"x":197,"y":64},"end":{"x":227,"y":64}} */
        		(162 + -540 * t + 378 * Math.pow(t, 2))/ (222 + -246 * t + 114 * Math.pow(t, 2)) 
        		, 146)
        		);
        
        public static final Path BACK_SWITCH_SECOND_CUBE = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":0},"mid1":{"x":40,"y":-21},"mid2":{"x":35,"y":10},"end":{"x":35,"y":28}} */
        		(-63 + 312 * t + -195 * Math.pow(t, 2))/ (120 + -270 * t + 150 * Math.pow(t, 2)) 
        		, 66)
        		);
        
        public static final Path BACK_SWITCH_BACKUP = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":218,"y":183},"mid1":{"x":199,"y":197},"mid2":{"x":177,"y":240},"end":{"x":152,"y":267}} */
        		(42 + 174 * t + -135 * Math.pow(t, 2))/ (-57 + -18 * t + 0 * Math.pow(t, 2)) 
        		, 108)
        		);
        
    	/** Scale Left**/
        public static final Path SCALE_LEFT_TRAVEL = straightLength(214);
        public static final Path SCALE_LEFT_FINISH = new Path(
        		/** Old path that works without second cube**/
//        		new PathSegment(t -> 
//        		/* {"start":{"x":0,"y":100},"mid1":{"x":10,"y":102},"mid2":{"x":13,"y":102},"end":{"x":33,"y":126}} */
//        		(6 + -12 * t + 78 * Math.pow(t, 2))/ (30 + -42 * t + 72 * Math.pow(t, 2)) 
//        		, 44)
        		
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":10,"y":102},"mid2":{"x":15,"y":115},"end":{"x":33,"y":126}} */
        		(6 + 66 * t + -39 * Math.pow(t, 2))/ (30 + -30 * t + 54 * Math.pow(t, 2)) 
        		, 43)
        		
        		);
        public static final Path SCALE_LEFT_SECOND_CUBE_GRAB = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":265,"y":98},"mid1":{"x":247,"y":102},"mid2":{"x":226,"y":66},"end":{"x":224,"y":64}} */
        		(12 + -240 * t + 222 * Math.pow(t, 2))/ (-54 + -18 * t + 66 * Math.pow(t, 2)) 
        		, 56)
        		);
        
        public static final Path SCALE_LEFT_SECOND_CUBE_PLACE = new Path( 
//        		new PathSegment(t -> 
//        		/* {"start":{"x":208.5,"y":93.25},"mid1":{"x":266.5,"y":93.25},"mid2":{"x":310.5,"y":100.25},"end":{"x":289.5,"y":65.25}} */
//        		(0 + 42 * t + -147 * Math.pow(t, 2))/ (174 + -84 * t + -153 * Math.pow(t, 2)) 
//        		, 109) use negative throttle
        		);
        
        		
        /** Scale Right**/
        public static final Path SCALE_RIGHT_TRAVEL = new Path(
                straightLength(160).getPathAtDistance(10));
        public static final Path SCALE_RIGHT_TRAVEL_2 = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":0},"mid1":{"x":84,"y":-5},"mid2":{"x":59,"y":83},"end":{"x":64,"y":155}} */
        		(-15 + 558 * t + -327 * Math.pow(t, 2))/ (252 + -654 * t + 417 * Math.pow(t, 2)) 
        		, 196)
        		);
        
        public static final Path SCALE_RIGHT_TRAVEL_2_CUT_SHORT = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":0},"mid1":{"x":84,"y":-5},"mid2":{"x":70,"y":77},"end":{"x":70,"y":92}} */
        		(-15 + 522 * t + -462 * Math.pow(t, 2))/ (252 + -588 * t + 336 * Math.pow(t, 2)) 
        		, 138)
        		);
        
        public static final Path SCALE_RIGHT_FINISH = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":0,"y":100},"mid1":{"x":58,"y":110},"mid2":{"x":48,"y":85},"end":{"x":48,"y":80}} */
        		(30 + -210 * t + 165 * Math.pow(t, 2))/ (174 + -408 * t + 234 * Math.pow(t, 2)) 
        		, 64)
        		);
        
        public static final Path SECOND_CUBE_LEFT = new Path(
        		
        		new PathSegment(t -> 
        		/* {"start":{"x":283,"y":78},"mid1":{"x":300,"y":102.5},"mid2":{"x":257,"y":102.5},"end":{"x":210,"y":90}} */
        		(73.5 + -147 * t + 36 * Math.pow(t, 2))/ (51 + -360 * t + 168 * Math.pow(t, 2)) 
        		, 93)
        		);
        
        public static final Path SECOND_PLACE_LEFT = new Path(
        		new PathSegment(t -> 
        		/* {"start":{"x":210,"y":90},"mid1":{"x":180,"y":20},"mid2":{"x":250,"y":60},"end":{"x":283,"y":78}} */
        		(-210 + 660 * t + -396 * Math.pow(t, 2))/ (-90 + 600 * t + -411 * Math.pow(t, 2)) 
        		, 123));

    		}

    public static Path straightLength(double length) {
        return new Path(new PathSegment(t -> 0.0, length));
    }
}