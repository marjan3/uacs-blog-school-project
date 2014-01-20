function(doc) {
	if(doc.postDate)
		emit(null, doc);
}