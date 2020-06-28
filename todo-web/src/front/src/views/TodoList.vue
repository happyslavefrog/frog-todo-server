<template>
    <div>
        <TodoItem
                :key="todoItem.id"
                :todoItem="todoItem"
                v-for="todoItem in todoItems"
        />
    </div>
</template>

<script>
    import TodoItem from "@/components/TodoItem";

    export default {
        name: "TodoList",
        components: {
            TodoItem
        },
        data() {
            return {
                todoItems: [
                    {
                        id: 1,
                        contents: "할일 A",
                        status: "todo"
                    },
                    {
                        id: 2,
                        contents: "할일 B",
                        status: "inProgress"
                    },
                    {
                        id: 3,
                        contents: "할일 C",
                        status: "done"
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>