import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysModuleComponent,
    SysModuleDetailComponent,
    SysModuleUpdateComponent,
    SysModuleDeletePopupComponent,
    SysModuleDeleteDialogComponent,
    sysModuleRoute,
    sysModulePopupRoute
} from './';

const ENTITY_STATES = [...sysModuleRoute, ...sysModulePopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysModuleComponent,
        SysModuleDetailComponent,
        SysModuleUpdateComponent,
        SysModuleDeleteDialogComponent,
        SysModuleDeletePopupComponent
    ],
    entryComponents: [SysModuleComponent, SysModuleUpdateComponent, SysModuleDeleteDialogComponent, SysModuleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysModuleModule {}
