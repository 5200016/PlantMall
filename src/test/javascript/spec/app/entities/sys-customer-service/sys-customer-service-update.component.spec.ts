/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCustomerServiceUpdateComponent } from 'app/entities/sys-customer-service/sys-customer-service-update.component';
import { SysCustomerServiceService } from 'app/entities/sys-customer-service/sys-customer-service.service';
import { SysCustomerService } from 'app/shared/model/sys-customer-service.model';

describe('Component Tests', () => {
    describe('SysCustomerService Management Update Component', () => {
        let comp: SysCustomerServiceUpdateComponent;
        let fixture: ComponentFixture<SysCustomerServiceUpdateComponent>;
        let service: SysCustomerServiceService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCustomerServiceUpdateComponent]
            })
                .overrideTemplate(SysCustomerServiceUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCustomerServiceUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCustomerServiceService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCustomerService(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCustomerService = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new SysCustomerService();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.sysCustomerService = entity;
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
