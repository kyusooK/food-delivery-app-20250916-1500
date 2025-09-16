<template>

    <v-card outlined>
        <v-card-title>
            CompleteCooking
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
                    @click="completeCooking"
            >
                CompleteCooking
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
    name: 'CompleteCookingCommand',
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
        completeCooking() {
            this.$emit('completeCooking', this.value);
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

