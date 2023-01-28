
RGGraph {

    var <>n, <>k, <>p, <>nodes;

    *new {|n=12,k=2,p=0.5|
		^super.newCopyArgs(n,k,p,List()).init();
    }

	init {
		this.n.do {|i|
			this.nodes.add(RGNode(
				i, List(), 0.1, { Pbind(\freq,100*i,\dur, Pseq([0.1],4)).play }
			))
		};

		this.nodes.do {|node|
			this.k.do {|i|
				var o = i + 1;
				node.edges = node.edges.add((node.id+o).wrap(0,this.n-1));
				node.edges = node.edges.add((node.id-o).wrap(0,this.n-1));
			};
		};
	}

	print {
		this.nodes.do {|node| node.print};
	}

	/*  Rewiring Algorithms */

	smallWorld {

		(this.k * 2).do {|k|
            this.nodes.do{|node|
            	if(this.p.coin, {
            		var newNode = this.n.rand;
            		if(node.edges.includes(newNode).not, {
            			("- node" + node.id + ": swapping" + node.edges[k] + "with" + newNode).postln;
						node.edges[k] = newNode;
            		})
            	})
            }
        };
	}

	/*  Playing Algorithms */

	run {|dur=0.5|

		Routine {

			var node = this.nodes[0];

			inf.do {
				"## node:" + node.id.postln;
				node.action.value;
				dur.wait;
				node = this.nodes[node.edges.choose];
			};

		}.play
	}

}
