<template>

    <v-card outlined>
        <v-card-title>
            CancelOrder
        </v-card-title>

        <v-card-text>
            <Number label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            <Number label="CustomerId" v-model="value.customerId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="cancelOrder"
            >
                CancelOrder
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
    name: 'CancelOrderCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.orderId = 0;
        this.value.customerId = 0;
    },
    watch: {
    },
    methods: {
        cancelOrder() {
            this.$emit('cancelOrder', this.value);
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

