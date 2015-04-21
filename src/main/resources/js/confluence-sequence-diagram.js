AJS.toInit(function() {
	AJS.$(".diagram").each(function() {
		var $this = AJS.$(this),
				theme = $this.data("theme");

		$this.sequenceDiagram({theme: (theme && theme !== "" ? theme : "simple")});
	});
});
