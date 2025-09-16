<template>

    <v-card outlined>
        <v-card-title>
            DeleteMenuItem
        </v-card-title>

        <v-card-text>
            <Number label="MenuItemId" v-model="value.menuItemId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="deleteMenuItem"
            >
                DeleteMenuItem
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
    name: 'DeleteMenuItemCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.menuItemId = 0;
    },
    watch: {
    },
    methods: {
        deleteMenuItem() {
            this.$emit('deleteMenuItem', this.value);
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

