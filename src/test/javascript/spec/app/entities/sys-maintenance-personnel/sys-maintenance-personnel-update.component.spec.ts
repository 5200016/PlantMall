/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysMaintenancePersonnelUpdateComponent } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel-update.component';
import { SysMaintenancePersonnelService } from 'app/entities/sys-maintenance-personnel/sys-maintenance-personnel.service';
import { SysMaintenancePersonnel } from 'app/shared/model/sys-maintenance-personnel.model';

describe('Component Tests', () => {
    describe('SysMaintenancePersonnel Management Update Component', () => {
        let comp: SysMaintenancePersonnelUpdateComponent;
        let fixture: ComponentFixture<SysMaintenancePersonnelUpdateComponent>;
        let service: SysMaintenancePersonnelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysMaintenancePersonnelUpdateComponent]
            })
                .overrideTemplate(SysMaintenancePersonnelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysMaintenancePersonnelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysMaintenancePersonnelService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMaintenancePersonnel(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMaintenancePersonnel = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysMaintenancePersonnel();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysMaintenancePersonnel = entity;
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
