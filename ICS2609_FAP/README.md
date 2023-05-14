# Fry Me to the Moon
project

# Git Tutorial
## Git vs GitHub
**Git** - version control, parang gdocs pero pang code. Pwede makita ung changes na may kasamang time, sino huling nag update, revert, gumawa ng different version, etc.

**GitHub** - parang frontend ui ng Git.

## Git setup
Follow this [Git setup](https://www.theodinproject.com/lessons/foundations-setting-up-git)
> Download Git bash if you're using Windows then proceed to step 2 in the given link

## Common Git commands
``` bash
# clone chosen repository (make sure you're a collaborator, if not ask repo owner)
git clone https://github.com/patlouis/ICS2609_FAP.git

# implement new features and updates by creating a new branch and switching to it
# good practice to avoid bugs getting mixed with master branch
git checkout -b "new_branch"

# START coding and making new changes in this step, after that...

# stage/ add updates, . means current directory, every file changed will be added
# you can also specify a file name
git add .

# commit to confirm changes
# make sure the commit message is concise and imperative
git commit -m "commit message"

# push updates to github
git push origin "branch_name"
```

## Creating pull requests
- make sure to add a screenshot and short comment/s of the new updates
- include people assigned to the task as reviewer *(optional)*
- merge the request if you're sure or ask a fellow collaborator 
