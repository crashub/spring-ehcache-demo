function colourize() {
	var dnl = document.getElementsByTagName("tr");
	for (i = 0; i < dnl.length; i++) {
		if ((Math.round(i / 2) * 2) == ((i / 2) * 2))
			dnl.item(i).style.background = "#E8E8FF";
	}
}
window.onload = colourize;