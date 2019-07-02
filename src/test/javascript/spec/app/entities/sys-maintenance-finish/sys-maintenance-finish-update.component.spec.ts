/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenanceFinishUpdateComponent } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish-update.component';
import { SysMaintenanceFinishService } from 'app/entities/sys-maintenance-finish/sys-maintenance-finish.service';
import { SysMaintenanceFinish } from 'app/shared/model/sys-maintenance-finish.model';

describe('Component Tests', () => {
    describe('SysMaintenanceFinish Management Update Component', () => {
        let comp: SysMaintenanceFinishUpdateComponent;
        let fixture: ComponentFixture<SysMaintenanceFinishUpdateComponent>;
        let service: SysMaintenanceFinishService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenanceFinishUpdateComponent]
            })
                .overrideTemplate(SysMaintenanceFinishUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMaintenanceFinishUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenanceFinishService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMaintenanceFinish(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMaintenanceFinish = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMaintenanceFinish();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMaintenanceFinish = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
