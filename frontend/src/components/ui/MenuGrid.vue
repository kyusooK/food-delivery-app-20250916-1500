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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="deleteMenuDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 삭제
                </v-btn>
                <v-dialog v-model="deleteMenuDialog" width="500">
                    <DeleteMenu
                        @closeDialog="deleteMenuDialog = false"
                        @deleteMenu="deleteMenu"
                    ></DeleteMenu>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="updateMenuDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 수정
                </v-btn>
                <v-dialog v-model="updateMenuDialog" width="500">
                    <UpdateMenu
                        @closeDialog="updateMenuDialog = false"
                        @updateMenu="updateMenu"
                    ></UpdateMenu>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="registerMenuDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>메뉴 등록
                </v-btn>
                <v-dialog v-model="registerMenuDialog" width="500">
                    <RegisterMenu
                        @closeDialog="registerMenuDialog = false"
                        @registerMenu="registerMenu"
                    ></RegisterMenu>
                </v-dialog>
            </div>
            <MenuList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></MenuList>
            <MenuDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></MenuDetails>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>RestaurantId</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>MenuItemId</th>
                        <th>메뉴 항목</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="RestaurantId">{{ val.restaurantId }}</td>
                            <td class="whitespace-nowrap" label="Name">{{ val.name }}</td>
                            <td class="whitespace-nowrap" label="Description">{{ val.description }}</td>
                            <td class="whitespace-nowrap" label="Status">{{ val.status }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="MenuItemId">{{ val.menuItemId }}</td>
                            <td class="whitespace-nowrap" label="메뉴 항목">
                                <MenuItemId :editMode="editMode" v-model="val.menuItemId"></MenuItemId>
                            </td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Menu 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Menu :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Menu 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="MenuId" v-model="selectedRow.menuId" :editMode="true"/>
                            <Number label="RestaurantId" v-model="selectedRow.restaurantId" :editMode="true"/>
                            <String label="Name" v-model="selectedRow.name" :editMode="true"/>
                            <String label="Description" v-model="selectedRow.description" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <MenuStatus offline label="Status" v-model="selectedRow.status" :editMode="true"/>
                            <MenuItemId offline label="MenuItemId" v-model="selectedRow.menuItemId" :editMode="true"/>
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
    name: 'menuGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'menus',
        deleteMenuDialog: false,
        updateMenuDialog: false,
        registerMenuDialog: false,
    }),
    watch: {
    },
    methods:{
        async deleteMenu(params){
            try{
                var path = "deleteMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DeleteMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.deleteMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async updateMenu(params){
            try{
                var path = "updateMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UpdateMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.updateMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async registerMenu(params){
            try{
                var path = "registerMenu".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RegisterMenu 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.registerMenuDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>