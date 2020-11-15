package com.reign.reigntest.common.models

import com.google.gson.annotations.SerializedName

data class HitItem(

	@field:SerializedName("comment_text")
	val commentText: String? = null,

	@field:SerializedName("story_text")
	val storyText: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("story_id")
	val storyId: Int? = null,

	@field:SerializedName("_tags")
	val tags: List<String?>? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("created_at_i")
	val createdAtI: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("points")
	val points: Int? = null,

	@field:SerializedName("_highlightResult")
	val highlightResult: HighlightResult? = null,

	@field:SerializedName("num_comments")
	val numComments: Int? = null,

	@field:SerializedName("story_title")
	val storyTitle: String? = null,

	@field:SerializedName("parent_id")
	val parentId: Int? = null,

	@field:SerializedName("story_url")
	val storyUrl: String? = null,

	@field:SerializedName("objectID")
	val objectID: String? = null
)