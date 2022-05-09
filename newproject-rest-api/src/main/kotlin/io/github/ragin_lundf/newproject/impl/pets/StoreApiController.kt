package io.github.ragin_lundf.newproject.impl.pets

import io.github.ragin_lundf.newproject.dto.pets.models.OrderDto
import io.github.ragin_lundf.newproject.pets.StoreApi
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StoreApiController: StoreApi {
    override fun deleteOrder(orderId: Long) {
        TODO("Not yet implemented")
    }

    override fun getInventory(): Map<String, Int> {
        TODO("Not yet implemented")
    }

    override fun getOrderById(orderId: Long): OrderDto {
        TODO("Not yet implemented")
    }

    override fun placeOrder(body: OrderDto): OrderDto {
        TODO("Not yet implemented")
    }
}
