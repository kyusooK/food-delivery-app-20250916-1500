<template>

    <v-card outlined>
        <v-card-title>
            ReceiveOrderByRestaurant
        </v-card-title>

        <v-card-text>
            <Number label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            <Number label="RestaurantId" v-model="value.restaurantId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="receiveOrderByRestaurant"
            >
                ReceiveOrderByRestaurant
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
    name: 'ReceiveOrderByRestaurantCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.orderId = 0;
        this.value.restaurantId = 0;
    },
    watch: {
    },
    methods: {
        receiveOrderByRestaurant() {
            this.$emit('receiveOrderByRestaurant', this.value);
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

