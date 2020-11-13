package com.reign.reigntest.common.models

import com.google.gson.annotations.SerializedName

data class HighlightResult(

	@field:SerializedName("comment_text")
	val commentText: CommentText? = null,

	@field:SerializedName("author")
	val author: Author? = null,

	@field:SerializedName("story_title")
	val storyTitle: StoryTitle? = null,

	@field:SerializedName("story_url")
	val storyUrl: StoryUrl? = null
)