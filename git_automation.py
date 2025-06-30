import subprocess

def run_git_command(command):
    result = subprocess.run(command, shell=True, capture_output=True, text=True)
    if result.returncode != 0:
        print(f"Error: {result.stderr}")
        exit(1)
    return result.stdout.strip()

def main():
    branch_name = "branch1"

    # Step 1: Switch to branch1 if not already there
    current_branch = run_git_command("git rev-parse --abbrev-ref HEAD")
    if current_branch != branch_name:
        print(f"Switching to {branch_name}...")
        run_git_command(f"git switch {branch_name}")
    else:
        print(f"Already on {branch_name}.")

    # Step 2: git add .
    print("Adding all changes...")
    run_git_command("git add .")

    # Step 3: git commit -m "Code updated"
    print("Committing changes...")
    commit_message = "Code updated"
    # Only commit if there are changes
    status = run_git_command("git status --porcelain")
    if status:
        run_git_command(f'git commit -m "{commit_message}"')
    else:
        print("No changes to commit.")

    # Step 4: git switch main
    print("Switching to main branch...")
    run_git_command("git switch main")

    # Step 5: git merge branch1
    print(f"Merging {branch_name} into main...")
    run_git_command(f"git merge {branch_name}")

    # Step 6: git push
    print("Pushing to remote...")
    run_git_command("git push")

    print("Done!")

if __name__ == "__main__":
    main()

