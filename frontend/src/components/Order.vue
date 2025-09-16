<template>
    <div>
        <Number
            label="OrderId"
            v-model="value.orderId"
            :editMode="editMode"
        />
        <Number
            label="CustomerId"
            v-model="value.customerId"
            :editMode="editMode"
        />
        <Number
            label="RestaurantId"
            v-model="value.restaurantId"
            :editMode="editMode"
        />
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <div variant="outlined" class="my-2">
            <Number
                label="MenuItems"
                v-model="menuItemsInput"
                :editMode="editMode"
                @keydown.enter="addMenuItemsList"
            />
            <v-card v-if="value.menuItems.length > 0" variant="outlined" class="pa-4 mt-2">
                <v-card-sub-title>
                    MenuItems List
                </v-card-sub-title>
                <li v-for="(name, index) in value.menuItems" :key="index">
                    {{ name }}
                </li>
            </v-card>
            <v-row class="ma-0 pa-0 mt-2">
                <v-spacer></v-spacer>
                <v-btn @click="addMenuItemsList">
                    MenuItems 추가
                </v-btn>
            </v-row>
            
            <LongDetailGrid
                label="MenuItems"
                offline
                v-model="value.menuItems"
                :editMode="editMode"
                @change="change"
            />
        </div>
        <v-divider class="border-opacity-50 my-divider my-2"></v-divider>
        <String
            label="DeliveryRoute"
            v-model="value.deliveryRoute"
            :editMode="editMode"
        />
        <String
            label="CustomerContact"
            v-model="value.customerContact"
            :editMode="editMode"
        />
        <String
            label="RestaurantContact"
            v-model="value.restaurantContact"
            :editMode="editMode"
        />
        <Number
            label="OrderTotalPrice"
            v-model="value.orderTotalPrice"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="UpdatedAt"
            v-model="value.updatedAt"
            :editMode="editMode"
        />
        <Number
            label="Version"
            v-model="value.version"
            :editMode="editMode"
        />
        <OrderStatus
            offline
            label="OrderStatus"
            v-model="value.orderStatus"
            :editMode="editMode"
            @change="change"
        />
        <PaymentInfo
            offline
            label="PaymentInfo"
            v-model="value.paymentInfo"
            :editMode="editMode"
            @change="change"
        />
        <OrderStatus
            offline
            label="PaymentStatus"
            v-model="value.paymentStatus"
            :editMode="editMode"
            @change="change"
        />
        <DeliveryStatus
            offline
            label="DeliveryStatus"
            v-model="value.deliveryStatus"
            :editMode="editMode"
            @change="change"
        />
        <DeliveryPersonInfo
            offline
            label="DeliveryPersonInfo"
            v-model="value.deliveryPersonInfo"
            :editMode="editMode"
            @change="change"
        />
        <MenuId
            offline
            label="MenuId"
            v-model="value.menuId"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Order',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "orders",
        menuItemsInput: null,
        value: {
            menuItems: [],
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
        addMenuItemsList() {
            if (this.menuItemsInput !== null && this.menuItemsInput !== '') {
                this.value.menuItems.push(this.menuItemsInput);
                this.menuItemsInput = null; // null로 초기화
            }
        },
    },
}
</script>
