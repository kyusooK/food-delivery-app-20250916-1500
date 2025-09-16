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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="completeDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 완료
                </v-btn>
                <v-dialog v-model="completeDeliveryDialog" width="500">
                    <CompleteDelivery
                        @closeDialog="completeDeliveryDialog = false"
                        @completeDelivery="completeDelivery"
                    ></CompleteDelivery>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="requestDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 요청
                </v-btn>
                <v-dialog v-model="requestDeliveryDialog" width="500">
                    <RequestDelivery
                        @closeDialog="requestDeliveryDialog = false"
                        @requestDelivery="requestDelivery"
                    ></RequestDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="cancelOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 취소
                </v-btn>
                <v-dialog v-model="cancelOrderDialog" width="500">
                    <CancelOrder
                        @closeDialog="cancelOrderDialog = false"
                        @cancelOrder="cancelOrder"
                    ></CancelOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="completeCookingDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>조리 완료
                </v-btn>
                <v-dialog v-model="completeCookingDialog" width="500">
                    <CompleteCooking
                        @closeDialog="completeCookingDialog = false"
                        @completeCooking="completeCooking"
                    ></CompleteCooking>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="startDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 시작
                </v-btn>
                <v-dialog v-model="startDeliveryDialog" width="500">
                    <StartDelivery
                        @closeDialog="startDeliveryDialog = false"
                        @startDelivery="startDelivery"
                    ></StartDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="acceptDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 수락
                </v-btn>
                <v-dialog v-model="acceptDeliveryDialog" width="500">
                    <AcceptDelivery
                        @closeDialog="acceptDeliveryDialog = false"
                        @acceptDelivery="acceptDelivery"
                    ></AcceptDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="rejectDeliveryDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>배달 거절
                </v-btn>
                <v-dialog v-model="rejectDeliveryDialog" width="500">
                    <RejectDelivery
                        @closeDialog="rejectDeliveryDialog = false"
                        @rejectDelivery="rejectDelivery"
                    ></RejectDelivery>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="pickupFoodDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>음식 픽업
                </v-btn>
                <v-dialog v-model="pickupFoodDialog" width="500">
                    <PickupFood
                        @closeDialog="pickupFoodDialog = false"
                        @pickupFood="pickupFood"
                    ></PickupFood>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="receiveOrderByRestaurantDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>음식점 주문 접수
                </v-btn>
                <v-dialog v-model="receiveOrderByRestaurantDialog" width="500">
                    <ReceiveOrderByRestaurant
                        @closeDialog="receiveOrderByRestaurantDialog = false"
                        @receiveOrderByRestaurant="receiveOrderByRestaurant"
                    ></ReceiveOrderByRestaurant>
                </v-dialog>
                <v-btn style="margin-left: 5px;" @click="placeOrderDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>주문 생성
                </v-btn>
                <v-dialog v-model="placeOrderDialog" width="500">
                    <PlaceOrder
                        @closeDialog="placeOrderDialog = false"
                        @placeOrder="placeOrder"
                    ></PlaceOrder>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="startCookingDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>조리 시작
                </v-btn>
                <v-dialog v-model="startCookingDialog" width="500">
                    <StartCooking
                        @closeDialog="startCookingDialog = false"
                        @startCooking="startCooking"
                    ></StartCooking>
                </v-dialog>
            </div>
            <OrderDetails @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderDetails>
            <OrderList @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderList>
            <OrderStatusView @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></OrderStatusView>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>CustomerId</th>
                        <th>RestaurantId</th>
                        <th>MenuItems</th>
                        <th>OrderStatus</th>
                        <th>PaymentInfo</th>
                        <th>PaymentStatus</th>
                        <th>DeliveryAddress</th>
                        <th>DeliveryStatus</th>
                        <th>DeliveryPersonInfo</th>
                        <th>DeliveryRoute</th>
                        <th>CustomerContact</th>
                        <th>RestaurantContact</th>
                        <th>OrderTotalPrice</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        <th>Version</th>
                        <th>MenuId</th>
                        <th>메뉴</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="CustomerId">{{ val.customerId }}</td>
                            <td class="whitespace-nowrap" label="RestaurantId">{{ val.restaurantId }}</td>
                            <td class="whitespace-nowrap" label="MenuItems">
                                <span v-for="(name, index) in val.menuItems" :key="index">
                                    {{ name }}<br>
                                </span>
                            </td>
                            <td class="whitespace-nowrap" label="OrderStatus">{{ val.orderStatus }}</td>
                            <td class="whitespace-nowrap" label="PaymentInfo">{{ val.paymentInfo }}</td>
                            <td class="whitespace-nowrap" label="PaymentStatus">{{ val.paymentStatus }}</td>
                            <td class="whitespace-nowrap" label="DeliveryAddress">{{ val.deliveryAddress }}</td>
                            <td class="whitespace-nowrap" label="DeliveryStatus">{{ val.deliveryStatus }}</td>
                            <td class="whitespace-nowrap" label="DeliveryPersonInfo">{{ val.deliveryPersonInfo }}</td>
                            <td class="whitespace-nowrap" label="DeliveryRoute">{{ val.deliveryRoute }}</td>
                            <td class="whitespace-nowrap" label="CustomerContact">{{ val.customerContact }}</td>
                            <td class="whitespace-nowrap" label="RestaurantContact">{{ val.restaurantContact }}</td>
                            <td class="whitespace-nowrap" label="OrderTotalPrice">{{ val.orderTotalPrice }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
                            <td class="whitespace-nowrap" label="Version">{{ val.version }}</td>
                            <td class="whitespace-nowrap" label="MenuId">{{ val.menuId }}</td>
                            <td class="whitespace-nowrap" label="메뉴">
                                <MenuId :editMode="editMode" v-model="val.menuId"></MenuId>
                            </td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <LongDetailGrid style="margin-top: 20px;" label="MenuItems" offline v-if="selectedRow" v-model="selectedRow.menuItems" :selectedRow="selectedRow"/>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Order 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Order :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Order 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="OrderId" v-model="selectedRow.orderId" :editMode="true"/>
                            <Number label="CustomerId" v-model="selectedRow.customerId" :editMode="true"/>
                            <Number label="RestaurantId" v-model="selectedRow.restaurantId" :editMode="true"/>
                            <Number label="MenuItems" v-model="selectedRow.menuItems" :editMode="true"/>
                            <String label="DeliveryRoute" v-model="selectedRow.deliveryRoute" :editMode="true"/>
                            <String label="CustomerContact" v-model="selectedRow.customerContact" :editMode="true"/>
                            <String label="RestaurantContact" v-model="selectedRow.restaurantContact" :editMode="true"/>
                            <Number label="OrderTotalPrice" v-model="selectedRow.orderTotalPrice" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <Number label="Version" v-model="selectedRow.version" :editMode="true"/>
                            <OrderStatus offline label="OrderStatus" v-model="selectedRow.orderStatus" :editMode="true"/>
                            <PaymentInfo offline label="PaymentInfo" v-model="selectedRow.paymentInfo" :editMode="true"/>
                            <OrderStatus offline label="PaymentStatus" v-model="selectedRow.paymentStatus" :editMode="true"/>
                            <DeliveryStatus offline label="DeliveryStatus" v-model="selectedRow.deliveryStatus" :editMode="true"/>
                            <DeliveryPersonInfo offline label="DeliveryPersonInfo" v-model="selectedRow.deliveryPersonInfo" :editMode="true"/>
                            <MenuId offline label="MenuId" v-model="selectedRow.menuId" :editMode="true"/>
                            <LongDetailGrid label="MenuItems" offline v-model="selectedRow.menuItems" :editMode="true"/>
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
    name: 'orderGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'orders',
        completeDeliveryDialog: false,
        requestDeliveryDialog: false,
        cancelOrderDialog: false,
        completeCookingDialog: false,
        startDeliveryDialog: false,
        acceptDeliveryDialog: false,
        rejectDeliveryDialog: false,
        pickupFoodDialog: false,
        receiveOrderByRestaurantDialog: false,
        placeOrderDialog: false,
        startCookingDialog: false,
    }),
    watch: {
    },
    methods:{
        async completeDelivery(params){
            try{
                var path = "completeDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CompleteDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.completeDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async requestDelivery(params){
            try{
                var path = "requestDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RequestDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.requestDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async cancelOrder(params){
            try{
                var path = "cancelOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CancelOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.cancelOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async completeCooking(params){
            try{
                var path = "completeCooking".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','CompleteCooking 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.completeCookingDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async startDelivery(params){
            try{
                var path = "startDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','StartDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.startDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async acceptDelivery(params){
            try{
                var path = "acceptDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','AcceptDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.acceptDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async rejectDelivery(params){
            try{
                var path = "rejectDelivery".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','RejectDelivery 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.rejectDeliveryDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async pickupFood(params){
            try{
                var path = "pickupFood".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PickupFood 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.pickupFoodDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async receiveOrderByRestaurant(params){
            try{
                var path = "receiveOrderByRestaurant".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ReceiveOrderByRestaurant 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.receiveOrderByRestaurantDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async placeOrder(params){
            try{
                var path = "placeOrder".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PlaceOrder 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.placeOrderDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async startCooking(params){
            try{
                var path = "startCooking".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','StartCooking 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.startCookingDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>