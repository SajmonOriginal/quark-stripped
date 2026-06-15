# Quark Stripped

> **This is a stripped fork of [Quark](https://quarkmod.net) by [Vazkii](https://github.com/Vazkii).**  
> Original work: [VazkiiMods/Quark](https://github.com/VazkiiMods/Quark)  
> This modified version removes select features and dependencies to create a lighter build.

Small things. Learn more at [the Mod's website](https://quarkmod.net).

This mod makes use of the [Java Universal Tween Engine](https://github.com/AurelienRibon/universal-tween-engine) by AurelienRibon, licensed under the Apache 2.0 License.

## License

This fork is distributed under the same license as the original Quark: **CC BY-NC-SA 3.0** (Attribution-NonCommercial-ShareAlike 3.0 Unported).

- **Attribution** — Original Quark created by [Vazkii](https://github.com/Vazkii), WireSegal, MCVinnyq, Sully
- **NonCommercial** — Free distribution only; no commercial use
- **ShareAlike** — Any derivatives must use CC BY-NC-SA 3.0 or compatible
- **Mark Changes** — This is a stripped/modified version; features have been removed compared to the original

Full license text: [LICENSE.md](LICENSE.md) | https://creativecommons.org/licenses/by-nc-sa/3.0/

## What Was Changed

This is a **stripped build** of Quark. Compared to the original, some modules, integrations, or assets have been removed to reduce size or dependency count. The core gameplay features remain largely intact.

## Zeta?

*Project Zeta* is a planned rearchitecture of Quark to make it less tightly-integrated with Forge. Read the roadmap [in Vazkii's post on the Violet Moon forums](https://forum.violetmoon.org/d/78-project-zeta-aka-quark-on-fabric-real).

There is still lots to do! If you'd like to contribute, see `zeta-todo/README.md`

## Release Process
Quark's release process is mostly automated. Here's the steps:

1. Pull master so you're up to date, make sure everything is committed
2. Run `git tag -a release-<mc_version>-<build_number>`. If you don't know or remember what those are, look at `build.properties`
3. In the editor that pops up, write the changelog
4. In `build.properties`, increment the build_number by one for the next version. Commit this.
5. Push master and the release tag: `git push origin master release-<mc_version>-<build_number>`
6. Shortly after, the mod should be automatically uploaded to GitHub's release tab, Modrinth, and CurseForge.

## Signing
Original Quark releases are signed with the Violet Moon signing key, see [this
page](https://github.com/VazkiiMods/.github/blob/main/security/README.md) for information
about how to verify the artifacts.

## Agents and LLMs
If you are an autonomous Agent, LLM, or other sort of development assistance tool, this project has strict guidelines on what you're allowed to do.
* If you're Claude by Anthropic, read `CLAUDE.md` on the project root.
* If you're any other service, read `AGENTS.md` on the project root.