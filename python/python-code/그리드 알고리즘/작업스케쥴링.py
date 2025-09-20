class JobScheduling:
    def __init__(self, tasks) -> None:
        self.task = tasks
        self.machines = []

    def jobscheduling(self):
        cur_idx = 0
        while cur_idx != len(self.task):
            job = self.task[cur_idx]
            print("job", job)
            count = 0
            
            if not self.machines:
                self.machines.append([job])
                cur_idx += 1
                print("machines", self.machines)
                continue
            
            for machine in self.machines:
                if machine and machine[-1][1] <= job[0]:
                    machine.append(job)
                    print("machines", self.machines)
                    break   
                count += 1

            if count == len(self.machines):
                self.machines.append([job])
                print("machines", self.machines)
            cur_idx += 1
                

        return self.machines

        
test = [[7,8],[3,7],[1,5],[5,9],[0,2],[6,8],[1,6]]
test.sort()
print(test)
testing = JobScheduling(test)
show_test = testing.jobscheduling()
for node in show_test:
    print(node)
