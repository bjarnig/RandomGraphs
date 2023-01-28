
RGNode {

	var <>id, <>edges, <>dur, <>action;

    *new {|id=1,edges,dur,action|
		^super.newCopyArgs(id,edges,dur,action).init();
    }

	init {

	}

	print {
		("## node:" + this.id + this.edges + this.dur + this.action).postln;
	}
}