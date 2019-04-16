import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysRoleComponent,
    SysRoleDetailComponent,
    SysRoleUpdateComponent,
    SysRoleDeletePopupComponent,
    SysRoleDeleteDialogComponent,
    sysRoleRoute,
    sysRolePopupRoute
} from './';

const ENTITY_STATES = [...sysRoleRoute, ...sysRolePopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysRoleComponent,
        SysRoleDetailComponent,
        SysRoleUpdateComponent,
        SysRoleDeleteDialogComponent,
        SysRoleDeletePopupComponent
    ],
    entryComponents: [SysRoleComponent, SysRoleUpdateComponent, SysRoleDeleteDialogComponent, SysRoleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysRoleModule {}
