<template>

    <v-card outlined>
        <v-card-title>
            PlaceOrder
        </v-card-title>

        <v-card-text>
            <Number label="CustomerId" v-model="value.customerId" :editMode="editMode"/>
            <Number label="RestaurantId" v-model="value.restaurantId" :editMode="editMode"/>
            <LongList offline label="MenuItems" v-model="value.menuItems" :editMode="editMode" @change="change"/>
            <PaymentInfo offline label="PaymentInfo" v-model="value.paymentInfo" :editMode="editMode" @change="change"/>
            <DeliveryAddress offline label="DeliveryAddress" v-model="value.deliveryAddress" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="placeOrder"
            >
                PlaceOrder
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'PlaceOrderCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.customerId = 0;
        this.value.restaurantId = 0;
        this.value.menuItems = [];
        this.value.paymentInfo = {};
        this.value.deliveryAddress = {};
    },
    watch: {
    },
    methods: {
        placeOrder() {
            this.$emit('placeOrder', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

