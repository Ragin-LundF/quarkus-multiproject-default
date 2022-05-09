package io.github.ragin_lundf.newproject.impl.pets

import io.github.ragin_lundf.newproject.dto.pets.models.UserDto
import io.github.ragin_lundf.newproject.pets.UserApi
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserApiController: UserApi {
    override fun createUser(body: UserDto) {
        TODO("Not yet implemented")
    }

    override fun createUsersWithArrayInput(body: List<UserDto>) {
        TODO("Not yet implemented")
    }

    override fun createUsersWithListInput(body: List<UserDto>) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(username: String) {
        TODO("Not yet implemented")
    }

    override fun getUserByName(username: String): UserDto {
        TODO("Not yet implemented")
    }

    override fun loginUser(username: String, password: String): String {
        TODO("Not yet implemented")
    }

    override fun logoutUser() {
        TODO("Not yet implemented")
    }

    override fun updateUser(username: String, body: UserDto) {
        TODO("Not yet implemented")
    }
}
