import com.example.gemi.ui.data.Content
import com.google.gson.annotations.SerializedName

data class GeminiImageRequest(
    @SerializedName("contents") val contents: List<Content1>
)

data class Content1(
    @SerializedName("parts") val parts: List<Part1>
)

data class Part1(
    @SerializedName("inlineData") val inlineData: InlineData
)

data class InlineData(
    @SerializedName("mimeType") val mimeType: String,
    @SerializedName("data") val data: String // Base64-encoded image
)
data class GeminiImageResponse(
    @SerializedName("candidates") val candidates: List<Candidate1>?
)

data class Candidate1(
    @SerializedName("content") val content: ContentResponse?
)

data class ContentResponse(
    @SerializedName("parts") val parts: List<TextPart>?
)

data class TextPart(
    @SerializedName("text") val text: String
)
