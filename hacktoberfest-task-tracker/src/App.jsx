import { useState } from "react";
import "./App.css";

function App() {
	const [tasks, setTasks] = useState([
		{ text: "Submit Hacktoberfest PR", done: true, isEditing: false },
		{ text: "Read React Docs", done: false, isEditing: false },
		{ text: "Build a mini React app", done: true, isEditing: false },
		{ text: "Push code to GitHub", done: false, isEditing: false },
		{ text: "Write README.md", done: false, isEditing: false },
	]);
	const [task, setTask] = useState("");
	const [editedText, setEditedText] = useState("");

	function toggleEdit(id) {
		setTasks((prevTasks) =>
			prevTasks.map((t, i) =>
				i === id ? { ...t, isEditing: !t.isEditing } : t
			)
		);
	}

	function editTask(id, task) {
		setTasks((prevTasks) =>
			prevTasks.map((t, i) => (i === id ? { ...t, text: task } : t))
		);
	}

	return (
		<div className="bg-gray-100 min-h-screen flex items-center justify-center font-sans">
			<div className="w-full max-w-2xl p-8 bg-white rounded-xl shadow-lg">
				<h1 className="text-4xl font-bold text-center text-gray-800 mb-8">
					Task Manager
				</h1>
				<div className="flex gap-4 mb-6">
					<input
						className="flex-grow p-3 border-2 border-gray-300 rounded-lg focus:outline-none focus:border-blue-500 transition-colors"
						placeholder="Enter task"
						value={task}
						onChange={(e) => {
							setTask(e.target.value);
						}}
					/>
					<button
						className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-3 px-6 rounded-lg transition-colors"
						onClick={() => {
							let temp = [...tasks];
							temp.push({ text: task, done: false });
							setTasks(temp);
						}}
					>
						Add task
					</button>
				</div>
				<div className="space-y-6">
					{/* Pending tasks */}
					<div className="space-y-4">
						<p className="text-3xl font-bold text-gray-700">
							Pending tasks
						</p>
						{tasks.filter((t) => !t.done).length === 0
							? "No pending tasks"
							: tasks.map((task, i) => {
									if (!task.done) {
										return task.isEditing ? (
											<div
												key={i}
												className="flex items-center justify-between bg-gray-50 p-4 rounded-lg shadow gap-1"
											>
												<div className="flex-grow flex items-center gap-3">
													<input
														className="w-full p-2 focus:border-2 focus:border-gray-300 rounded-lg focus:outline-none  transition-colors ml-2"
														value={task.text}
														onChange={(e) =>
															editTask(
																i,
																e.target.value
															)
														}
														onKeyDown={(e) => {
															if (
																e.key ===
																"Enter"
															) {
																toggleEdit(i);
															}
														}}
														onBlur={() =>
															toggleEdit(i)
														}
													/>
												</div>
												<div>
													<button
														className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-lg transition-colors"
														onClick={() => {
															setTasks(
																(prevTasks) =>
																	prevTasks.filter(
																		(t) =>
																			t.text !==
																			task.text
																	)
															);
														}}
													>
														Delete task
													</button>
												</div>
											</div>
										) : (
											<div
												key={i}
												className="flex items-center justify-between bg-gray-50 p-4 rounded-lg shadow"
											>
												<div className="flex items-center gap-3">
													<input
														type="checkbox"
														className="h-5 w-5 text-blue-500 border-gray-300 rounded focus:ring-blue-500"
														defaultChecked={
															task.done
														}
														onClick={(e) => {
															if (
																e.target.checked
															) {
																setTasks(
																	(
																		prevTasks
																	) =>
																		prevTasks.map(
																			(
																				t
																			) =>
																				t.text ===
																				task.text
																					? {
																							...t,
																							done: true,
																					  }
																					: t
																		)
																);
															}
														}}
													/>
													<div
														className={`text-lg text-gray-800 ${
															task.done
																? "line-through"
																: ""
														}`}
														onClick={() =>
															toggleEdit(i)
														}
													>
														{task.text}
													</div>
												</div>
												<div>
													<button
														className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-lg transition-colors"
														onClick={() => {
															setTasks(
																(prevTasks) =>
																	prevTasks.filter(
																		(t) =>
																			t.text !==
																			task.text
																	)
															);
														}}
													>
														Delete task
													</button>
												</div>
											</div>
										);
									}
							  })}
					</div>
					{/* Completed tasks */}
					<div className="space-y-4">
						<p className="text-3xl font-bold text-gray-700">
							Completed tasks
						</p>
						{tasks.filter((t) => t.done).length === 0
							? "No task completed"
							: tasks.map((task, i) => {
									if (task.done) {
										return (
											<div
												key={i}
												className="flex items-center justify-between bg-gray-50 p-4 rounded-lg shadow"
											>
												<div className="flex items-center gap-3">
													<input
														type="checkbox"
														className="h-5 w-5 text-blue-500 border-gray-300 rounded focus:ring-blue-500"
														defaultChecked={
															task.done
														}
														onClick={(e) => {
															if (
																!e.target
																	.checked
															) {
																setTasks(
																	(
																		prevTasks
																	) => {
																		return prevTasks.map(
																			(
																				t
																			) =>
																				t.text ===
																				task.text
																					? {
																							...t,
																							done: false,
																					  }
																					: t
																		);
																	}
																);
															}
														}}
													/>
													<div
														className={`text-lg text-gray-800 ${
															task.done
																? "line-through"
																: ""
														}`}
													>
														{task.text}
													</div>
												</div>
												<div>
													<button
														className="bg-red-500 hover:bg-red-600 text-white font-bold py-2 px-4 rounded-lg transition-colors"
														onClick={() => {
															setTasks(
																(prevTasks) =>
																	prevTasks.filter(
																		(t) =>
																			t.text !==
																			task.text
																	)
															);
														}}
													>
														Delete task
													</button>
												</div>
											</div>
										);
									}
							  })}
					</div>
				</div>
			</div>
		</div>
	);
}

export default App;
