<template>
    <div>
        <DefaultHeaderContainer/>
        <TodoItemContainer/>
        <DefaultFooterContainer/>
    </div>
</template>

<script>
    import DefaultHeaderContainer from "@/container/DefaultHeaderContainer";
    import DefaultFooterContainer from "@/container/DefaultFooterContainer";
    import TodoList from "@/views/TodoList";

    export default {
        name: "DefaultContainer",
        components: {
            DefaultHeaderContainer,
            DefaultFooterContainer,
            TodoItemContainer: TodoList
        }
    }
</script>

<style scoped>

</style>