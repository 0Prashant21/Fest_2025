def activity_selection(start, end, n):
    # Create a list of activities with their start and end times
    activities = [(start[i], end[i]) for i in range(n)]

    # Sort activities by their end times
    activities.sort(key=lambda x: x[1])

    print("Selected activities:")

    # The first activity is always selected
    last_end_time = activities[0][1]
    print(f"Activity: Start = {activities[0][0]}, End = {activities[0][1]}")

    # Consider the rest of the activities
    for i in range(1, n):
        if activities[i][0] >= last_end_time:
            # Select the activity if its start time is greater than or equal to the last activity's end time
            print(f"Activity: Start = {activities[i][0]}, End = {activities[i][1]}")
            last_end_time = activities[i][1]

# Driver code
if __name__ == "__main__":
    # Start and end times of the activities
    start = [1, 3, 0, 5, 8, 5]
    end = [2, 4, 6, 7, 9, 9]

    n = len(start)

    # Perform activity selection
    activity_selection(start, end, n)
