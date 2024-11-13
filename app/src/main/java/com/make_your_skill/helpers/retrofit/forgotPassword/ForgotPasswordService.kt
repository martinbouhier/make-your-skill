import com.make_your_skill.dataClasses.forgotPassword.ForgotPasswordDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordService {
    @POST("email/sendPassword")
    suspend fun emailSendPassword(
        @Body forgotPasswordBody: ForgotPasswordDataClass
    ): Response<Void>
}