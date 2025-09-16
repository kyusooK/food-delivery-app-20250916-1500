<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateMenuItemDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 항목 수정
                </v-btn>
                <v-dialog v-model="updateMenuItemDialog" width="500">
                    <UpdateMenuItem
                        @closeDialog="updateMenuItemDialog = false"
                        @updateMenuItem="updateMenuItem"
                    ></UpdateMenuItem>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deleteMenuItemDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 항목 삭제
                </v-btn>
                <v-dialog v-model="deleteMenuItemDialog" width="500">
                    <DeleteMenuItem
                        @closeDialog="deleteMenuItemDialog = false"
                        @deleteMenuItem="deleteMenuItem"
                    ></DeleteMenuItem>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="registerMenuItemDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 항목 등록
                </v-btn>
                <v-dialog v-model="registerMenuItemDialog" width="500">
                    <RegisterMenuItem
                        @closeDialog="registerMenuItemDialog = false"
                        @registerMenuItem="registerMenuItem"
                    ></RegisterMenuItem>
                </v-dialog>
            </div>
            <MenuItemDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></MenuItemDetails>
            <MenuItemList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></MenuItemList>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>MenuId</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>ImageUrl</th>
                        <th>Status</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="MenuId">{{ val.menuId }}</td>
                            <td class="whitespace-nowrap" label="Name">{{ val.name }}</td>
                            <td class="whitespace-nowrap" label="Description">{{ val.description }}</td>
                            <td class="whitespace-nowrap" label="Price">{{ val.price }}</td>
                            <td class="whitespace-nowrap" label="ImageUrl">{{ val.imageUrl }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">MenuItem 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <MenuItem :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">MenuItem 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="MenuItemId" v-model="selectedRow.menuItemId" :editMode="true"/>
                            <Number label="MenuId" v-model="selectedRow.menuId" :editMode="true"/>
                            <String label="Name" v-model="selectedRow.name" :editMode="true"/>
                            <String label="Description" v-model="selectedRow.description" :editMode="true"/>
                            <Number label="Price" v-model="selectedRow.price" :editMode="true"/>
                            <String label="ImageUrl" v-model="selectedRow.imageUrl" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <MenuItemStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'menuItemGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'menuItems',
        updateMenuItemDialog: false,
        deleteMenuItemDialog: false,
        registerMenuItemDialog: false,
    }),
    watch: {
    },
    methods:{
        async updateMenuItem(params){
            try{
                var path = "updateMenuItem".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateMenuItem 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateMenuItemDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async deleteMenuItem(params){
            try{
                var path = "deleteMenuItem".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeleteMenuItem 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deleteMenuItemDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async registerMenuItem(params){
            try{
                var path = "registerMenuItem".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterMenuItem 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerMenuItemDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>