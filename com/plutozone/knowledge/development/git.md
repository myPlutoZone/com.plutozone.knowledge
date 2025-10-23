# com.plutozone.knowledge.development.Git

## Version & Branch
```bash
$ git init						# initialization
$ git status					# show working tree status
$ git add [FILE]				# add [FILE] to staging area
$ git add .					# add all to staging area
$ git add src
$ git commit					# create version(=history) with editor
$ git commit -m "Message"		# create version(=history) with message
$ git commit -am "Message"		# create version(=history) + add(except untracted) with message
$ git log						# show version
$ git log --stat				# show version statics
$ git log -p					# show version with the changes(diffs)
$ git diff						# show change
$ git checkout  [ID]			# change the working tree to a specific version
$ git reset --hard [ID]			# delete a specific version
$ git revert [ID]				# revert without deleting(=reset) the version
```
## Backup & Restore
## Collaboration