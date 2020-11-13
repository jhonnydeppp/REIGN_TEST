package com.reign.reigntest.common.models

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("hits")
	val hits: List<HitsItem?>? = null,

	@field:SerializedName("hitsPerPage")
	val hitsPerPage: Int? = null,

	@field:SerializedName("processingTimeMS")
	val processingTimeMS: Int? = null,

	@field:SerializedName("query")
	val query: String? = null,

	@field:SerializedName("nbHits")
	val nbHits: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("params")
	val params: String? = null,

	@field:SerializedName("nbPages")
	val nbPages: Int? = null,

	@field:SerializedName("exhaustiveNbHits")
	val exhaustiveNbHits: Boolean? = null
)