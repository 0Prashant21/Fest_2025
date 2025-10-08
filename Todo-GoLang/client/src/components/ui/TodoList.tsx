import { Flex, Spinner, Stack, Text } from '@chakra-ui/react';
import TodoItem from './TodoItem';
import { useQuery } from '@tanstack/react-query';
import { BASE_URL } from '../../base_url/base_url';

export type Todo = {
  _id: number;
  body: string;
  completed: boolean;
};

const TodoList = () => {
  const { data: todos, isLoading } = useQuery<Todo[]>({
    queryKey: ['todos'],
    queryFn: async () => {
      try {
        const res = await fetch(BASE_URL + '/todos');
        const data = await res.json();

        if (!res.ok) {
          throw new Error(data.message || 'Something went wrong!');
        }

        return data || [];
      } catch (err) {
        console.error(err);
      }
    },
  });

  return (
    <>
      <Text
        fontSize={'4xl'}
        textTransform={'uppercase'}
        fontWeight={'bold'}
        textAlign={'center'}
        my={2}
      >
        Today's Tasks
      </Text>
      {isLoading && (
        <Flex justifyContent={'center'} my={4}>
          <Spinner size={'xl'} />
        </Flex>
      )}
      {!isLoading && todos?.length === 0 && (
        <Stack alignItems={'center'} gap='3'>
          <Text fontSize={'xl'} textAlign={'center'} color={'gray.500'}>
            All tasks completed! 🤞
          </Text>
          <img src='todo.png' alt='Todo logo' width={70} height={70} />
        </Stack>
      )}
      <Stack gap={3}>
        {todos?.map((todo) => (
          <TodoItem key={todo._id} todo={todo} />
        ))}
      </Stack>
    </>
  );
};
export default TodoList;
