/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysPlantLogUpdateComponent } from 'app/entities/sys-plant-log/sys-plant-log-update.component';
import { SysPlantLogService } from 'app/entities/sys-plant-log/sys-plant-log.service';
import { SysPlantLog } from 'app/shared/model/sys-plant-log.model';

describe('Component Tests', () => {
    describe('SysPlantLog Management Update Component', () => {
        let comp: SysPlantLogUpdateComponent;
        let fixture: ComponentFixture<SysPlantLogUpdateComponent>;
        let service: SysPlantLogService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysPlantLogUpdateComponent]
            })
                .overrideTemplate(SysPlantLogUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysPlantLogUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysPlantLogService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysPlantLog(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysPlantLog = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysPlantLog();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysPlantLog = entity;
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
