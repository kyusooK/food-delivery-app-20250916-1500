<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '주문/배달 관리' }" />
        <VerticalNavLink
            :item="{
                title: '주문',
                to: '/orders',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 상세 조회',
                to: '/orderDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 목록 조회',
                to: '/orderLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '주문 상태 실시간 조회',
                to: '/orderStatusViews',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '메뉴 관리' }" />
        <VerticalNavLink
            :item="{
                title: '메뉴',
                to: '/menus',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 항목',
                to: '/menuItems',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 목록 조회',
                to: '/menuLists',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 상세 조회',
                to: '/menuDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 항목 상세 조회',
                to: '/menuItemDetails',
            }"
        />
        <VerticalNavLink
            :item="{
                title: '메뉴 항목 목록 조회',
                to: '/menuItemLists',
            }"
        />
    </ul>
</template>
